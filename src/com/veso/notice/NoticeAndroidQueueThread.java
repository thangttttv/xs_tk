package com.veso.notice;

import java.util.List;

import com.veso.bean.Notice;
import com.veso.bean.NoticeQueue;
import com.veso.bean.NoticeUser;
import com.veso.dao.NoticeAndroidDAO;

public class NoticeAndroidQueueThread {
	public static void sendNoticeToQueue(){
		NoticeAndroidDAO notiAndroidDAO = new NoticeAndroidDAO();
		List<Notice> listNotice = notiAndroidDAO.getNoticeToSendQueue();
		for (Notice notice : listNotice) {
			List<NoticeUser>  listUser = notiAndroidDAO.getNoticeUser();
			int i = 0;
			while(i<listUser.size()){
				NoticeUser user = listUser.get(i);
				NoticeQueue noticeQueue = new NoticeQueue();
				noticeQueue.notice_id = notice.id;
				noticeQueue.device_token = user.device_token;
				noticeQueue.app_client_id = user.app_client_id;
				String payload = "{\"title\":\"V_TITLE\",\"description\":\"V_DESCRIPTION\",\"url\":\"V_URL\",\"type\":\"V_TYPE\"}";
				
				payload = payload.replaceAll("V_TITLE", notice.title);
				payload = payload.replaceAll("V_DESCRIPTION", notice.description);
				payload = payload.replaceAll("V_URL", notice.url);
				payload = payload.replaceAll("V_TYPE", notice.type+"");
				
				noticeQueue.payload = payload;
				noticeQueue.time_sent = notice.time_sent;
				/// check notice exist
				if(!notiAndroidDAO.checkNoticeQueueExist(noticeQueue.notice_id, noticeQueue.app_client_id))
				{
					notiAndroidDAO.insertNoticeQueue(noticeQueue);
				}
				i++;
			}
		}
	}
	
	public static void main(String[] args) {
		NoticeAndroidQueueThread.sendNoticeToQueue();
	}
}
