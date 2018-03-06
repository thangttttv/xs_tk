package com.veso.notice;

import java.util.List;

import com.veso.bean.Notice;
import com.veso.bean.NoticeQueue;
import com.veso.bean.NoticeUser;
import com.veso.dao.NoticeIOSDAO;

public class NoticeIOSQueueThread {
	
	public static void sendNoticeToQueue(){
		NoticeIOSDAO noticeIOSDAO = new NoticeIOSDAO();
		List<Notice> listNotice = noticeIOSDAO.getNoticeToSendQueue();
		for (Notice notice : listNotice) {
			List<NoticeUser>  listUser = noticeIOSDAO.getNoticeUser();
			int i = 0;
			while(i<listUser.size()){
				NoticeUser user = listUser.get(i);
				NoticeQueue noticeQueue = new NoticeQueue();
				noticeQueue.notice_id = notice.id;
				noticeQueue.device_token = user.device_token;
				noticeQueue.payload = "";
				noticeQueue.time_sent = notice.time_sent;
				/// check notice exist
				if(!noticeIOSDAO.checkNoticeQueueExist(noticeQueue.notice_id, noticeQueue.device_token))
				{
					noticeIOSDAO.insertNoticeQueue(noticeQueue);
				}
				i++;
			}
		}
	}
	
	public static void main(String[] args) {
		NoticeIOSQueueThread.sendNoticeToQueue();
	}
	
}
