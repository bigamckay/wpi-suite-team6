package edu.wpi.cs.wpisuitetng.modules.calendar;

import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class MockRequest extends Request {
	
	protected boolean sent = false;

	/**
	 * Constructor for MockRequest.
	 * @param networkConfiguration NetworkConfiguration
	 * @param path String
	 * @param requestMethod HttpMethod
	 */
	public MockRequest(NetworkConfiguration networkConfiguration, String path, HttpMethod requestMethod) {
		super(networkConfiguration, path, requestMethod);
	}

	/**
	 * Method send.
	
	 * @throws IllegalStateException */
	@Override
	public void send() throws IllegalStateException {
		// don't actually send
		sent = true;
	}
	
	/**
	 * Method isSent.
	
	 * @return boolean */
	public boolean isSent() {
		return sent;
	}
}
