package com.example.poedemo.wlanprint;

import android.content.Intent;
import btmanager.ConnectThread;
import btmanager.Pos;
import btmanager.ReadThread;
import btmanager.WriteThread;

public class DaemonThread extends Thread {

	@Override
	public void run() {

		_initHandler();
		_manageConnection();

	}

	private static void _initHandler() {
		while (true) {
			if ((ConnectThread.connectHandler != null)
					&& (WriteThread.writeHandler != null)
					&& (ReadThread.readHandler != null)) {
				BtService.getServiceContext().sendBroadcast(
						new Intent(BtService.ACTION_SERVICEREADY));
				break;
			}
		}
	}

	/**
	 * �ж�ʲôʱ����Ҫ���͹ر������źţ�ʲôʱ����Ҫ���͵ȴ������ź� �ȴ�ʲô�ģ�̫���ˣ�ֱ����������
	 * 
	 * @param t
	 * @param runnable
	 * 
	 */
	private static void _manageConnection() {
		while (BtService.isRunning()) {
			// ����߳���ʱ��Ҫ
			if (!BtService.stopAutoConnect) {
				// ˵���������Զ�����
				if (AutoConnect.getAutoConnectMode().equals(
						AutoConnect.VALUE_autoConnectModeActive)) {
					// Ӧ���ж������̵߳�״̬��
					// ������ڽ����������ӣ����ܷ��͸���Ϣ����Ϊ����Ϣ��ȡ���ǰ���
					// ��POS_Open��ͬ����Ϊ��Ȼ�������㣬�϶�Ҫȡ���ǰ���
					/**
					 * �ж���Ҫ�������ӵ���������ú�����д�
					 */

					if (!Pos.POS_isConnected()) {
						// û�����ӣ������ٽ����Զ����ӣ��޷��ж������������Ļ��Ǳ��������ģ�
						// ���¶���ͬ�ȶԴ�

						if (!Pos.POS_isConnecting()) {
							// ���Ҳ�������ڽ������ӣ����Ǵ�������״̬
							// ���Դ�

							Pos.POS_Open(AutoConnect.getAutoConnectMac());
						}

					}

				} else if (AutoConnect.getAutoConnectMode().equals(
						AutoConnect.VALUE_autoConnectModeWait)) {
					if (!Pos.POS_isConnected()) {
						if (!Pos.POS_isConnecting()) {
							Pos.POS_OpenAsServer(AutoConnect
									.getAutoConnectMac());
						}

					}
				}
				// TimeUtils.waitTime(2000);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
}
