package com.kkgame.feeop.sdkinfo.dao.impl;

import com.kkgame.feeop.data.bean.DataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.sdkinfo.bean.ProjectSdkVO;
import com.kkgame.feeop.sdkinfo.bean.ProjectThreeStatVO;
import com.kkgame.feeop.sdkinfo.bean.ThreeSdkVO;
import com.kkgame.feeop.sdkinfo.dao.ThreeSdkDAO;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/4/1
 *          Time: 16:21
 * @author: XJ
 * @since 3.0
 */
public class ThreeSdkDAOImpl extends SqlMapClientDaoSupport implements ThreeSdkDAO {

    @Override
    public List<ThreeSdkVO> getThreeSdkVOList(ThreeSdkVO threeSdkVO) throws DatabaseException {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject("threeSdkSqlMap.getThreeSdkVOListCount",threeSdkVO);
        threeSdkVO.getPage().setTotalRow(count);
        return getSqlMapClientTemplate().queryForList("threeSdkSqlMap.getThreeSdkVOList", threeSdkVO);
    }

    @Override
    public ThreeSdkVO getThreeSdkVOById(int id) throws DatabaseException {
        return (ThreeSdkVO) getSqlMapClientTemplate().queryForObject("threeSdkSqlMap.getThreeSdkVOById", id);
    }

    @Override
    public void insertThreeSdk(ThreeSdkVO threeSdkVO) throws DatabaseException {
        getSqlMapClientTemplate().insert("threeSdkSqlMap.insertThreeSdk", threeSdkVO);
    }

    @Override
    public void updateThreeSdk(ThreeSdkVO threeSdkVO) throws DatabaseException {
        getSqlMapClientTemplate().update("threeSdkSqlMap.updateThreeSdk", threeSdkVO);
    }

    @Override
    public List<ProjectSdkVO> getProjectSdkVOList(ProjectSdkVO projectSdkVO) throws DatabaseException {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject("threeSdkSqlMap.getProjectSdkVOListCount",projectSdkVO);
        projectSdkVO.getPage().setTotalRow(count);
        return getSqlMapClientTemplate().queryForList("threeSdkSqlMap.getProjectSdkVOList", projectSdkVO);
    }

    @Override
    public ProjectSdkVO getProjectSdkVOById(int projectId, int sdkId) throws DatabaseException {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("projectId", projectId);
        map.put("sdkId", sdkId);
        return (ProjectSdkVO) getSqlMapClientTemplate().queryForObject("threeSdkSqlMap.getProjectSdkVOById", map);
    }

    @Override
    public void insertProjectSdk(ProjectSdkVO projectSdkVO) throws DatabaseException {
        getSqlMapClientTemplate().insert("threeSdkSqlMap.insertProjectSdk", projectSdkVO);
    }

    @Override
    public void updateProjectSdk(ProjectSdkVO projectSdkVO) throws DatabaseException {
        getSqlMapClientTemplate().update("threeSdkSqlMap.updateProjectSdk", projectSdkVO);
    }

    @Override
    public void delectProjectSdk(ProjectSdkVO projectSdkVO) throws DatabaseException {
        getSqlMapClientTemplate().delete("threeSdkSqlMap.delectProjectSdk", projectSdkVO);
    }

    @Override
    public void writeProjectSdkLogs(ProjectSdkVO projectSdkVO) throws DatabaseException {
        getSqlMapClientTemplate().insert("threeSdkSqlMap.writeProjectSdkLogs", projectSdkVO);
    }

    @Override
    public List<ThreeSdkVO> getAllThreeSdkVOList() throws DatabaseException {
        return getSqlMapClientTemplate().queryForList("threeSdkSqlMap.getAllThreeSdkVOList");
    }

    @Override
    public List<ProjectThreeStatVO> getProjectThreeStatVOList(SearchVO searchVO) throws DatabaseException {
        String month = CalendarFormat.switchFormatDate(searchVO.getStartDate(),"yyyy-MM-dd","yyyyMM");
        searchVO.setTable("project_three_"+month);
        String[] rowFields = searchVO.getRowFieldString().substring(0, searchVO.getRowFieldString().length()-1).split(",");
        searchVO.setRowFields(rowFields);
        searchVO.getRowFieldVO().setShowRowField(searchVO.getRowFields());
        return getSqlMapClientTemplate().queryForList("threeSdkSqlMap.getProjectThreeStatVOList", searchVO);
    }

    @Override
    public void insertProjectThreeList(List<DataVO> list, String statDate) throws DatabaseException {
        String month = CalendarFormat.switchFormatDate(statDate,"yyyy-MM-dd","yyyyMM");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table", "project_three_"+month);
        map.put("list", list);
        getSqlMapClientTemplate().insert("threeSdkSqlMap.insertProjectThreeList", map);
    }

    @Override
    public void delectProjectThreeList(String statDate) throws DatabaseException {
        String month = CalendarFormat.switchFormatDate(statDate,"yyyy-MM-dd","yyyyMM");
        String table = "project_three_" + month;
        Map<String, String> map = new HashMap<>();
        map.put("table", table);
        map.put("saleDate", statDate);
        getSqlMapClientTemplate().insert("threeSdkSqlMap.delectProjectThreeList", map);
    }

    @Override
    public List<ProjectSdkVO> getAllExportProjectSdkVOList(String exportDate) throws DatabaseException {
        String month = CalendarFormat.switchFormatDate(exportDate,"yyyy-MM-dd","yyyyMM");
        Map<String, String> map = new HashMap<>();
        map.put("month", month);
        map.put("exportData", exportDate);
        return getSqlMapClientTemplate().queryForList("threeSdkSqlMap.getAllExportProjectSdkVOList", map);
    }

    @Override
    public List<ProjectSdkVO> getExportProjectSdkLogsList(String startDate, String endDate) throws DatabaseException {
        Map<String, String> map = new HashMap<>();
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        return getSqlMapClientTemplate().queryForList("threeSdkSqlMap.getExportProjectSdkLogsList", map);
    }

    @Override
    public List<ProjectSdkVO> getInsideSdkProjectVOList(ProjectSdkVO projectSdkVO) throws DatabaseException {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject("threeSdkSqlMap.getInsideSdkProjectVOListCount",projectSdkVO);
        projectSdkVO.getPage().setTotalRow(count);
        return getSqlMapClientTemplate().queryForList("threeSdkSqlMap.getInsideSdkProjectVOList", projectSdkVO);
    }

    @Override
    public void deleteInside(ProjectSdkVO projectSdkVO) throws DatabaseException {
        getSqlMapClientTemplate().delete("threeSdkSqlMap.deleteInside", projectSdkVO);
    }

    @Override
    public void insertInside(ProjectSdkVO projectSdkVO) throws DatabaseException {
        getSqlMapClientTemplate().insert("threeSdkSqlMap.insertInside", projectSdkVO);
    }

    @Override
    public void updateInside(ProjectSdkVO projectSdkVO) throws DatabaseException {
        getSqlMapClientTemplate().update("threeSdkSqlMap.updateInside", projectSdkVO);
    }

    @Override
    public ProjectSdkVO getInsideVOById(int sdkId) throws DatabaseException {
        return (ProjectSdkVO)getSqlMapClientTemplate().queryForObject("threeSdkSqlMap.getInsideVOById", sdkId);
    }

}
