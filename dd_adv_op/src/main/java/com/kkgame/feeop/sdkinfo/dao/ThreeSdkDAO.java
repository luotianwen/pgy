package com.kkgame.feeop.sdkinfo.dao;

import com.kkgame.feeop.data.bean.DataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.sdkinfo.bean.ProjectSdkVO;
import com.kkgame.feeop.sdkinfo.bean.ProjectThreeStatVO;
import com.kkgame.feeop.sdkinfo.bean.ThreeSdkVO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/4/1
 *          Time: 16:21
 * @author: XJ
 * @since 3.0
 */
public interface ThreeSdkDAO {
    List<ThreeSdkVO> getThreeSdkVOList(ThreeSdkVO threeSdkVO) throws DatabaseException;

    ThreeSdkVO getThreeSdkVOById(int id) throws DatabaseException;

    void insertThreeSdk(ThreeSdkVO threeSdkVO) throws DatabaseException;

    void updateThreeSdk(ThreeSdkVO threeSdkVO) throws DatabaseException;

    List<ProjectSdkVO> getProjectSdkVOList(ProjectSdkVO projectSdkVO) throws DatabaseException;

    ProjectSdkVO getProjectSdkVOById(int projectId, int sdkId) throws DatabaseException;

    void insertProjectSdk(ProjectSdkVO projectSdkVO) throws DatabaseException;

    void updateProjectSdk(ProjectSdkVO projectSdkVO) throws DatabaseException;

    void delectProjectSdk(ProjectSdkVO projectSdkVO) throws DatabaseException;

    void writeProjectSdkLogs(ProjectSdkVO projectSdkVO) throws DatabaseException;

    List<ThreeSdkVO> getAllThreeSdkVOList() throws DatabaseException;

    List<ProjectThreeStatVO> getProjectThreeStatVOList(SearchVO searchVO) throws DatabaseException;

    void insertProjectThreeList(List<DataVO> list, String statDate) throws DatabaseException;

    void delectProjectThreeList(String statDate) throws DatabaseException;

    List<ProjectSdkVO> getAllExportProjectSdkVOList(String exportDate) throws DatabaseException;

    List<ProjectSdkVO> getExportProjectSdkLogsList(String startDate, String endDate) throws DatabaseException;

    List<ProjectSdkVO> getInsideSdkProjectVOList(ProjectSdkVO projectSdkVO)throws DatabaseException;

    void deleteInside(ProjectSdkVO projectSdkVO)throws DatabaseException;

    void insertInside(ProjectSdkVO projectSdkVO)throws DatabaseException;

    void updateInside(ProjectSdkVO projectSdkVO)throws DatabaseException;

    ProjectSdkVO getInsideVOById(int sdkId)throws DatabaseException;
}
