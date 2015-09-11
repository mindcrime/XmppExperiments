package org.example.fogbeam.xmpp;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;

public class MyNewMessageListener implements ChatMessageListener {

	@Override
	public void processMessage( Chat chat, Message msg ) {
		
		if( msg == null )
		{
			return;
		}
		
		String msgBody = msg.getBody();
		
		System.out.println( "received incoming message: " + msgBody );
		
		try
		{
			if( msgBody != null && !msgBody.isEmpty())
			{
				// A little pause here, it's creepy if the bot responds too fast.
				Thread.sleep( 1250 );
				
				chat.sendMessage( "Hello to you too!");
				
				
			}
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		

	}

}
