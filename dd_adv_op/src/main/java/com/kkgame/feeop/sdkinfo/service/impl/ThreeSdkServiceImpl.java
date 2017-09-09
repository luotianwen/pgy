package com.kkgame.feeop.sdkinfo.service.impl;

import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.sdkinfo.bean.ProjectSdkVO;
import com.kkgame.feeop.sdkinfo.bean.ProjectThreeStatVO;
import com.kkgame.feeop.sdkinfo.bean.ThreeSdkVO;
import com.kkgame.feeop.sdkinfo.dao.ThreeSdkDAO;
import com.kkgame.feeop.sdkinfo.service.ThreeSdkService;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/4/1
 *          Time: 16:19
 * @author: XJ
 * @since 3.0
 */
public class ThreeSdkServiceImpl implements ThreeSdkService {

    private ThreeSdkDAO threeSdkDAO;

    @Override
    public List<ThreeSdkVO> getThreeSdkVOList(ThreeSdkVO threeSdkVO) throws DatabaseException {
        return threeSdkDAO.getThreeSdkVOList(threeSdkVO);
    }

    @Override
    public ThreeSdkVO getThreeSdkVOById(int id) throws DatabaseException {
        return threeSdkDAO.getThreeSdkVOById(id);
    }

    @Override
    public void insertThreeSdk(ThreeSdkVO threeSdkVO) throws DatabaseException {
        threeSdkDAO.insertThreeSdk(threeSdkVO);
    }

    @Override
    public void updateThreeSdk(ThreeSdkVO threeSdkVO) throws DatabaseException {
        threeSdkDAO.updateThreeSdk(threeSdkVO);
    }

    public ThreeSdkDAO getThreeSdkDAO() {
        return threeSdkDAO;
    }

    public void setThreeSdkDAO(ThreeSdkDAO threeSdkDAO) {
        this.threeSdkDAO = threeSdkDAO;
    }

    @Override
    public List<ProjectSdkVO> getProjectSdkVOList(ProjectSdkVO projectSdkVO) throws DatabaseException {
        return threeSdkDAO.getProjectSdkVOList(projectSdkVO);
    }

    @Override
    public ProjectSdkVO getProjectSdkVOById(int projectId, int sdkId) throws DatabaseException {
        return threeSdkDAO.getProjectSdkVOById(projectId, sdkId);
    }

    @Override
    public void insertProjectSdk(ProjectSdkVO projectSdkVO) throws DatabaseException {
        threeSdkDAO.insertProjectSdk(projectSdkVO);
        // write logs
        int projectId = projectSdkVO.getProjectId();
        int sdkId = projectSdkVO.getSdkId();
        ProjectSdkVO proSdk = threeSdkDAO.getProjectSdkVOById(projectId, sdkId);

        StringBuilder sb = new StringBuilder("成功创建{[").append(proSdk.getProjectName()).append(":").append(projectId)
                .append("][").append(proSdk.getSdkName()).append(":").append(sdkId)
                .append("][sdkKey:").append(proSdk.getSdkKey()).append("][note:").append(proSdk.getNote()).append("]}");
        proSdk.setOperateNote(sb.toString());
        SimpleDateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        proSdk.setOperateDate(ymdhmsFormat.format(new Date()));
        threeSdkDAO.writeProjectSdkLogs(proSdk);
    }

    @Override
    public void updateProjectSdk(ProjectSdkVO projectSdkVO) throws DatabaseException {
        // write logs
        int projectId = projectSdkVO.getProjectId();
        int sdkId = projectSdkVO.getSdkId();
        ProjectSdkVO proSdk = threeSdkDAO.getProjectSdkVOById(projectId, sdkId);

        StringBuilder sb = new StringBuilder("成功修改[").append(proSdk.getProjectName()).append(":").append(projectId)
                .append("][").append(proSdk.getSdkName()).append(":").append(sdkId)
                .append("]的{[sdkKey:").append(proSdk.getSdkKey()).append("][note:").append(proSdk.getNote())
                .append("]}为{[sdkKey:").append(projectSdkVO.getSdkKey()).append("][note:").append(projectSdkVO.getNote()).append("]}");
        proSdk.setOperateNote(sb.toString());
        SimpleDateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        proSdk.setOperateDate(ymdhmsFormat.format(new Date()));

        threeSdkDAO.updateProjectSdk(projectSdkVO);
        threeSdkDAO.writeProjectSdkLogs(proSdk);
    }

    @Override
    public void delectProjectSdk(ProjectSdkVO projectSdkVO) throws DatabaseException {
        threeSdkDAO.delectProjectSdk(projectSdkVO);
        // write logs
        StringBuilder sb = new StringBuilder("成功删除{[").append(projectSdkVO.getProjectName()).append(":")
                .append(projectSdkVO.getProjectId()).append("][").append(projectSdkVO.getSdkName()).append(":")
                .append(projectSdkVO.getSdkId()).append("][sdkKey:").append(projectSdkVO.getSdkKey()).append("][note:")
                .append(projectSdkVO.getNote()).append("]}");
        projectSdkVO.setOperateNote(sb.toString());
        SimpleDateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        projectSdkVO.setOperateDate(ymdhmsFormat.format(new Date()));
        threeSdkDAO.writeProjectSdkLogs(projectSdkVO);
    }

    @Override
    public void writeProjectSdkLogs(ProjectSdkVO projectSdkVO) throws DatabaseException {
        threeSdkDAO.writeProjectSdkLogs(projectSdkVO);
    }

    @Override
    public List<ThreeSdkVO> getAllThreeSdkVOList() throws DatabaseException {
        return threeSdkDAO.getAllThreeSdkVOList();
    }

    @Override
    public List<ProjectThreeStatVO> getProjectThreeStatVOList(SearchVO searchVO) throws DatabaseException {
        return threeSdkDAO.getProjectThreeStatVOList(searchVO);
    }

    @Override
    public List<ProjectSdkVO> getAllExportProjectSdkVOList(String exportDate) throws DatabaseException {
        return threeSdkDAO.getAllExportProjectSdkVOList(exportDate);
    }

    @Override
    public List<ProjectSdkVO> getExportProjectSdkLogsList(String startDate, String endDate) throws DatabaseException {
        return threeSdkDAO.getExportProjectSdkLogsList(startDate, endDate);
    }

    @Override
    public void batchInsertProjectSdk(List<ProjectSdkVO> list) throws DatabaseException {
        ProjectSdkVO tmpVO; int projectId, sdkId;
        for (ProjectSdkVO projectSdkVO : list) {
            projectId = projectSdkVO.getProjectId();
            sdkId = projectSdkVO.getSdkId();
            tmpVO = getProjectSdkVOById(projectId, sdkId);
            if (null == tmpVO) {
                insertProjectSdk(projectSdkVO);
            } else {
                projectSdkVO.setOldProjectId(projectId);
                projectSdkVO.setOldSdkId(sdkId);
                updateProjectSdk(projectSdkVO);
            }
        }
    }

    @Override
    public List<ProjectSdkVO> getInsideSdkProjectVOList(ProjectSdkVO projectSdkVO) throws DatabaseException {
        return threeSdkDAO.getInsideSdkProjectVOList(projectSdkVO);
    }

    @Override
    public void deleteInside(ProjectSdkVO projectSdkVO) throws DatabaseException {
        threeSdkDAO.deleteInside(projectSdkVO);
    }

    @Override
    public void insertInside(ProjectSdkVO projectSdkVO) throws DatabaseException {
        threeSdkDAO .insertInside(projectSdkVO);
    }

    @Override
    public void updateInside(ProjectSdkVO projectSdkVO) throws DatabaseException {
        threeSdkDAO.updateInside(projectSdkVO);
    }

    @Override
    public ProjectSdkVO getInsideVOById(int sdkId) throws DatabaseException {
        return  threeSdkDAO.getInsideVOById(sdkId);
    }
}
