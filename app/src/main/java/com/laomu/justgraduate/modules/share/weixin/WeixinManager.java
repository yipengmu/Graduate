package com.laomu.justgraduate.modules.share.weixin;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXTextObject;

public class WeixinManager {
//信则聆日常 appid:wx4acd7a106495b9ed
//信则聆线上 appid:wx9fbdf4a2b1907b95

    public static final String APP_ID = "wx9fbdf4a2b1907b95";
    
	private IWXAPI api;
	WXTextObject textObj;
	private Context mContext;

	public WeixinManager(Context c) {
		mContext = c;
		// 初始化一个WXTextObject对象
		textObj = new WXTextObject();
		api = WXAPIFactory.createWXAPI(mContext, APP_ID);
		
	}

	public void sendReq(String msgInfo,boolean... isTimeline) {
		textObj.text = msgInfo;

		// 用WXTextObject对象初始化一个WXMediaMessage对象
		WXMediaMessage msg = new WXMediaMessage();
		msg.mediaObject = textObj;
		// 发送文本类型的消息时，title字段不起作用
		// msg.title = "Will be ignored";
		msg.description = msgInfo;

		// 构造一个Req
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = buildTransaction("text"); // transaction字段用于唯一标识一个请求
		req.message = msg;
		boolean isSendTimeLine = false ;
		if(isTimeline != null && isTimeline.length >0){
			isSendTimeLine = isTimeline[0];
		}
		req.scene = isSendTimeLine ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;

		// 调用api接口发送数据到微信
		if(!api.sendReq(req)){
			//未检测到微信app
			Toast.makeText(mContext, "未检测到应用程序，请安装后再分享", Toast.LENGTH_SHORT).show();
		}
	}
	

	private String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
	}
	
	public static Intent getWeixinIntent(){
		Intent intent = new Intent();
		ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setComponent(cmp);
		
		return intent;
	}
}
