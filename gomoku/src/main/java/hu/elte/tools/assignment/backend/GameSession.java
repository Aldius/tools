package hu.elte.tools.assignment.backend;

import hu.elte.tools.assignment.frontend.GomokuClientIface;
import hu.elte.tools.assignment.shared.model.GomokuModel;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by cmwal on 2017. 08. 20..
 */
public class GameSession extends Thread {

	private RMIClient player1;
	private GomokuClientIface player1Iface;
	private RMIClient player2;
	private GomokuClientIface player2Iface;
	private int sessionId;
	private GomokuModel model = new GomokuModel();


	public GameSession(RMIClient player1, RMIClient player2, int sessionId) throws RemoteException, NotBoundException, MalformedURLException {
		this.player1 = player1;
		this.player2 = player2;
		this.sessionId = sessionId;
		player1Iface = (GomokuClientIface) Naming.lookup(player1.address);
		player2Iface = (GomokuClientIface) Naming.lookup(player2.address);
		player1Iface.receiveSessionId(sessionId);
		player2Iface.receiveSessionId(sessionId);
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setModel(GomokuModel model, String playerName) throws RemoteException {
		this.model = model;
		if (playerName.equals(player1.name)) {
			player2Iface.synchronizeModel(model);
			player2Iface.yourTurn();
		} else {
			player1Iface.synchronizeModel(model);
			player1Iface.yourTurn();
		}
	}

	@Override
	public void run() {
		try {
			player1Iface.startGame();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
