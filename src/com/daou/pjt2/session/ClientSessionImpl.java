package com.daou.pjt2.session;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ClientSessionImpl implements ClientSession {

	private Socket socket;
	private final String HOST;
	private final int PORT;
	private ValidateServerResponse responseCheker;

	public BufferedReader reader;
	public BufferedWriter writer;

	// TODO:: imap ��Ʈ�� ���������� ������ �ְ� �����ؾ���.
	public ClientSessionImpl(String host, int port, ValidateServerResponse responseCheker) {
		this.HOST = host;
		this.PORT = port;
		this.responseCheker = responseCheker;
		connect();
	}

	@Override
	public String sendCommand(String command) {
		try {
			writer.write(command + "\r\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return readResponseLine();
	}
	
	//FIXME:: �����ε��� ���ؼ� ���ϰ��� �ٸ��� ������ ����ڰ� �򰥸��� ����. ������ ������� ������.
	public List<String> sendCommand(String command, boolean flag) {
		try {
			writer.write(command + "\r\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return readResponseAllLine();
	}
	
	@Override
	public String readResponseLine() {
		String response = "";

		try {
			response = reader.readLine();
			
			//FIXME:: �� �α׿� ���� �ٸ��� ����ϵ��� �����ؾ� �� - ������ only continue �ۿ� ����
			if (!responseCheker.validateResponse(response)) {
				return "";
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public List<String> readResponseAllLine() {
		String response = "";
		List <String> responseList = new LinkedList<>();

		try {
			response = reader.readLine();
			responseList.add(response);
			
			while (reader.ready() && (response = reader.readLine()) != null) {
				
				//FIXME:: �� �α׿� ���� �ٸ��� ����ϵ��� �����ؾ� �� - ������ only continue �ۿ� ����
				if (!responseCheker.validateResponse(response)) {
					continue;
					//break;
				}
				
				responseList.add(response);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responseList;
	}

	@Override
	public void connect() {
		socket = new Socket();

		try {
			socket.connect(new InetSocketAddress(HOST, PORT));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF8"));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void disconnect() {
		try {
			socket.close();
			reader.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
