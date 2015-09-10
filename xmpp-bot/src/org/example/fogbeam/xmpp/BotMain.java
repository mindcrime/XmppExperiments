package org.example.fogbeam.xmpp;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.util.TLSUtils;

public class BotMain 
{
	public static void main( String[] args ) throws Exception
	{
		
		XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
		configBuilder.setUsernameAndPassword("testuser1", "password");
		configBuilder.setResource("SomeResource");
		configBuilder.setServiceName("fogbeam.org");
		configBuilder.setHost( "www.fogbeam.org" );
		
		
		// accept all certificate - just for testing  
		try 
		{  
		    TLSUtils.acceptAllCertificates(configBuilder);  
		} 
		catch (NoSuchAlgorithmException e) {  
		
		} 
		catch (KeyManagementException e) {  
		
		}  
		
		AbstractXMPPConnection connection = new XMPPTCPConnection(configBuilder.build());
		// Connect to the server
		connection.connect();
		
		// Log into the server
		connection.login();
		
		ChatManager chatManager = ChatManager.getInstanceFor(connection);
		chatManager.addChatListener(
			new ChatManagerListener() {
				@Override
				public void chatCreated(Chat chat, boolean createdLocally)
				{
					if (!createdLocally)
						chat.addMessageListener(new MyNewMessageListener());;
				}
			});		
		
		System.out.println( "running..." );

		while( true )
		{
			Thread.sleep( 100000 );
			
		}
	}
}