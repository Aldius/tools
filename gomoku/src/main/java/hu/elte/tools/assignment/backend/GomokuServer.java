package hu.elte.tools.assignment.backend;

import hu.elte.tools.assignment.shared.model.GomokuModel;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by cmwal on 2017. 08. 20..
 */
public class GomokuServer extends UnicastRemoteObject implements GomokuServerIface {

	private LinkedList<RMIClient> matchMakingQueue = new LinkedList<>();
	private LinkedList<GameSession> gameSessions = new LinkedList<>();

	public GomokuServer() throws RemoteException {
		assert true;
	}

	public boolean login(String userName, String password) throws RemoteException {
		return false;
	}

	public boolean register(String userName, String password) throws RemoteException {
		return false;
	}

	public synchronized void searchForMatch(String userName, String address) throws RemoteException, MalformedURLException, NotBoundException {
		matchMakingQueue.add(new RMIClient(userName, address));
		if (matchMakingQueue.size() == 2) {
			gameSessions.add(new GameSession(matchMakingQueue.get(0), matchMakingQueue.get(1), new Random().nextInt()));
			gameSessions.getLast().start();
			matchMakingQueue.clear();
		}
	}

	public synchronized void synchronizeModel(GomokuModel model, int sessionId, String name) throws RemoteException {
		for (GameSession session : gameSessions) {
			if (session.getSessionId() == sessionId) {
				session.setModel(model, name);
				break;
			}
		}
	}
}
