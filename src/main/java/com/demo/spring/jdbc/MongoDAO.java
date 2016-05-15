package com.demo.spring.jdbc;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.WebRequest;

import com.demo.model.mongo.DBSource;
import com.demo.model.mongo.QueryInstances;
import com.demo.model.mongo.TreeNode;
import com.demo.model.mongo.UserQuery;
import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;
import com.mongodb.WriteResult;

@Repository
public class MongoDAO {
	
	public static Map<String,DataSource> dsMap;
	public static Map<String,PreparedStatement> instanceStatementMap = new HashMap<String,PreparedStatement>();
	public static Map<String,Thread> instanceThreadMap = new HashMap<String,Thread>();
	public static DataSource getBoneCPDataSource(DBSource ds) {
		BoneCPConfig config = new BoneCPConfig();
		config.setJdbcUrl(ds.getConnUrl()); 
		config.setUsername(ds.getUserName()); 
		config.setPassword(ds.getPassword());
		BoneCPDataSource bcpds = new BoneCPDataSource(config);
		bcpds.setDriverClass(ds.getDriverClass());
		return bcpds;
	}
	
	
	@Resource
    MongoTemplate mt;
    //@Autowired
	HttpSession httpSession;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
	public List<TreeNode> getDatabases() {
		return mt.find(
				new Query(Criteria.where("isActive").is("Y")),
				TreeNode.class, "sds_database_source");
	}

	public boolean saveQueryInfo(UserQuery userQuery) {
		try {
			mt.insert(userQuery, "sds_user_query");
			return true;
		}catch(Exception e){
			logger.error("Error occurred when save a query/n"+e);
			return false;
		}
	}

	public DBSource getDatabaseConfig(Long dsId) {
		return mt.findOne(new Query(Criteria.where("dsId").is(dsId)),DBSource.class,"sds_database_source");
	}

	public List<UserQuery> getQuery() {
		String soeid = httpSession.getAttribute("soeid").toString();
		return mt.find(Query.query(Criteria.where("soeid").is(soeid).and("isActive").is("Y")), UserQuery.class,"sds_user_query");
	}

	public int getMaxQueryId() {
		List<UserQuery> queryList = mt.find(new Query().with(new Sort(new Sort.Order(Direction.DESC,"queryId"))),UserQuery.class,"sds_user_query");
		return (queryList == null||queryList.size()==0)?1:queryList.get(0).getQueryId()+1;
		
	}
	private Long getMaxDataSourceId() {
		List<DBSource> queryList = mt.find(new Query().with(new Sort(new Sort.Order(Direction.DESC,"dsId"))),DBSource.class,"sds_database_source");
		return (queryList == null||queryList.size()==0)?1:queryList.get(0).getDsId()+1;
		
	}
	
	private Long getMaxDataSourceTreeId() {
		List<TreeNode> queryList = mt.find(new Query().with(new Sort(new Sort.Order(Direction.DESC,"labelId"))),TreeNode.class,"sds_database_conn_tree_node");
		return (queryList == null||queryList.size()==0)?1:queryList.get(0).getLabelId()+1;
		
	}
	 
	public boolean removeQueryById(String queryId) {
		Update queryUpdate = new Update();
		queryUpdate.set("isActive","N");
		WriteResult isUpdated = mt.updateFirst(new Query(Criteria.where("queryId").is(Long.valueOf(queryId))),queryUpdate,"sds_user_query");
		if (isUpdated != null && isUpdated.getN() > 0) return true;
		else return false;
	}

	public Long insertQueryInstance(String soeid, String queryContent) {
		QueryInstances instant = new QueryInstances();
		Long runId = getRunId();
		instant.setSoeid(soeid);
		instant.setQueryContent(queryContent);
		instant.setRunId(runId);
		instant.setCreateTime(new Date());
		instant.setStatus(1);
		mt.insert(instant, "sds_query_instances");
		return runId;
	}
	
	private Long getRunId() {
		QueryInstances latestInstantce = mt.findOne(new Query()
				.with(new Sort(new Sort.Order(Direction.DESC, "runId"))),
				QueryInstances.class, "sds_query_instances");
		return latestInstantce == null ? 1 : latestInstantce.getRunId() + 1;
	}
	
	public List<QueryInstances> getMyRecentQueries() {
		return mt.find(
				new Query(Criteria.where("soeid").is(
						httpSession.getAttribute("soeid").toString())).with(
						new Sort(new Sort.Order(Direction.DESC, "runId")))
						.limit(10),
				QueryInstances.class, "sds_query_instances");
	}

	public void updateQueryInstance(Long runId, int status, String failReason,
			String outputPath,String fileSize) {
		Update update = new Update();
		update.set("status", status);
		update.set("failReason", failReason);
		update.set("outputPath", outputPath);
		update.set("fileSize", fileSize);
		mt.updateFirst(new Query(Criteria.where("runId").is(runId)), update, "sds_query_instances");
		
	}

	public List<QueryInstances> getDataPreview(Long runId) {
		return null;
	}

	public QueryInstances getInstanceByRunId(Long runId) {
		List<QueryInstances> list = mt.find(new Query(Criteria.where("runId").is(runId)), QueryInstances.class, "sds_query_instances");
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

	public void updateExecutingQueryToFailed() {
		Update update = new Update();
		update.set("status", 3);
		update.set("failReason", "Server restart. Please run again");
		mt.updateMulti(new Query(Criteria.where("status").is(1)), update, QueryInstances.class, "sds_query_instances");
	}

	public void updateQueryInstance(Map<String, Object> queryParameterMap) {
		Update update = new Update();
		update.set("queryTime", (Long)queryParameterMap.get("queryTime"));
		mt.updateFirst(new Query(Criteria.where("runId").is((Long)queryParameterMap.get("runId"))), update, "sds_query_instances");
		
	}

	public List<DBSource> getAllDataSources() {
		return mt.find(new Query(Criteria.where("isActive").is("Y")),
				DBSource.class, "sds_database_source");
	}

	public DBSource insertDataSourceInfo(WebRequest webRequest) {
		Long dsId = getMaxDataSourceId();
		insertDataSourceInfo(dsId,webRequest);
		insertDataSourceTree(dsId,webRequest);
		DBSource ds = getDataSourceByDsId(dsId);
		return ds;
	}
	
	private DBSource getDataSourceByDsId(Long dsId) {
		return mt.findOne(new Query(Criteria.where("dsId").is(dsId)), DBSource.class,"sds_database_source");
	}

	private void insertDataSourceInfo(Long dsId,WebRequest webRequest){
		DBSource ds = new DBSource();
		ds.setConnName(webRequest.getParameter("connName"));
		ds.setConnUrl(webRequest.getParameter("connUrl"));
		ds.setDriverClass(webRequest.getParameter("driverClass"));
		ds.setUserName(webRequest.getParameter("userName"));
		ds.setPassword(webRequest.getParameter("password"));
		ds.setIsActive("Y");
		ds.setCreatedBy(httpSession.getAttribute("soeid").toString());
		ds.setCreatedTime(new Date());
		ds.setDsId(dsId);
		mt.insert(ds, "sds_database_source");
	}
	
	private void insertDataSourceTree (Long dsId,WebRequest webRequest){
		TreeNode dsTree = new TreeNode();
		dsTree.setDsId(dsId);
		String parentName = webRequest.getParameter("appName");
		Long parentId = 0L;
		if(parentName!=null && parentName!=""){
			parentId =  mt.findOne(new Query(Criteria.where("labelName").is(parentName)),
				TreeNode.class,"sds_database_conn_tree_node").getLabelId();
		}
		dsTree.setParentId(parentId);
		dsTree.setIsActive("Y");
		Long labelId = getMaxDataSourceTreeId();
		dsTree.setLabelId(labelId);
		dsTree.setText(webRequest.getParameter("connName"));
		mt.insert(dsTree,"sds_database_conn_tree_node");
	}

	public boolean updateDataSourceChanges(List<DBSource> dsList) {
        try
        {
            if (dsList != null && dsList.size() > 0)
                for (DBSource ds : dsList)
                {
                    Update recordUpdate = new Update();
                    recordUpdate.set("driverClass", ds.getDriverClass());
                    recordUpdate.set("connUrl", ds.getConnUrl());
                    recordUpdate.set("userName", ds.getUserName());
                    recordUpdate.set("password", ds.getPassword());
                    recordUpdate.set("updatedBy", httpSession.getAttribute("soeid").toString());
                    recordUpdate.set("updateTime", new Date());
                    mt.updateFirst(new Query(Criteria.where("dsId").is(ds.getDsId()).and("isActive")
                                    .is("Y")), recordUpdate, "sds_database_source");
                    updateDataSourceConnMap(ds.getDsId());
                }
            return true;
        } catch (Exception e)
        {
            logger.error("Update data sorce failed " + e);
            return false;
        }
	}

	private void updateDataSourceConnMap(Long dsId) {
		DBSource ds = getDataSourceByDsId( dsId);
		dsMap.put(ds.getDsId().toString(), getBoneCPDataSource(ds));
	}

	public List<TreeNode> getDataSourceConnTree() {
		return mt.find(new Query(Criteria.where("isActive").is("Y")).with(new Sort(new Sort.Order(Direction.ASC,"parentId"))), TreeNode.class,"sds_database_conn_tree_node");
	}

	public List<TreeNode> getDataSourceConnRootTree() {
		return mt.find(new Query(Criteria.where("isActive").is("Y").and("parentId").is(0)).with(new Sort(new Sort.Order(Direction.ASC,"parentId"))), TreeNode.class,"sds_database_conn_tree_node");
	}

	public boolean removeDataSourceById(String dsId) {
		Update queryUpdate = new Update();
		queryUpdate.set("isActive","N");
		WriteResult isUpdated = mt.updateFirst(new Query(Criteria.where("dsId").is(Long.valueOf(dsId))),queryUpdate,"sds_database_source");
		WriteResult isUpdated2 = mt.updateFirst(new Query(Criteria.where("dsId").is(Long.valueOf(dsId))),queryUpdate,"sds_database_conn_tree_node");
		if (isUpdated != null && isUpdated.getN() > 0 && isUpdated2 != null && isUpdated2.getN() > 0) return true;
		else return false;
	}

	public void updateQueryInstanceByTreadId(long id) {
		Update queryUpdate = new Update();
		queryUpdate.set("threadId","N");
		mt.updateFirst(new Query(Criteria.where("runId").is(id)), queryUpdate, "sds_query_instances");
	}

	public void updateQueryInstanceStatus(String runId, int runStatus) {
		Update queryUpdate = new Update();
		queryUpdate.set("status",runStatus);
		mt.updateFirst(new Query(Criteria.where("runId").is(Long.valueOf(runId))), queryUpdate, "sds_query_instances");
		
	}

	public void updateQueryInstanceToFinish(Long runId, int runStatusFinished, String fileFullName, String fileSize) {
		QueryInstances instance = mt.findOne(new Query(Criteria.where("runId").is(runId)), QueryInstances.class,"sds_query_instances");
		if(instance.getStatus()!=4){
			Update update = new Update();
			update.set("status", runStatusFinished);
			update.set("outputPath", fileFullName);
			update.set("fileSize", fileSize);
			mt.updateFirst(new Query(Criteria.where("runId").is(runId)), update, "sds_query_instances");
		}
	}

	public void updateQueryInstanceFailed(Long runId, int runStatusFailed, String message) {
		QueryInstances instance = mt.findOne(new Query(Criteria.where("runId").is(runId)), QueryInstances.class,"sds_query_instances");
		if(instance.getStatus()!=4){
			Update update = new Update();
			update.set("status", runStatusFailed);
			update.set("failReason", message);
			mt.updateFirst(new Query(Criteria.where("runId").is(runId)), update, "sds_query_instances");
		}
	}

	public String getQueryTableViewByType(String driverClass,String type) {
		DBSource ds = mt.findOne(new Query(Criteria.where("driverClass").is(driverClass).and("type").is(type)),DBSource.class,"sds_database_query_table_view");
		return ds==null?null:ds.getQueryContent();
	}

	public void updateUsernamePassword(String dsId, String username, String password) {
		Update update = new Update();
		update.set("username", username);
		update.set("password", password);
		mt.updateFirst(new Query(Criteria.where("dsId").is(Long.valueOf(dsId))),update,"sds_database_source");
		DBSource sdsDataConn = this.getDatabaseConfig(Long.valueOf(dsId));
		dsMap.put(dsId,getBoneCPDataSource(sdsDataConn));
	}
 
}






