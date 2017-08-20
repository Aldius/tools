package hu.elte.tools.assignment.frontend;

import hu.elte.tools.assignment.shared.model.GomokuModel;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by cmwal on 2017. 08. 11..
 */
public interface GomokuClientIface extends Remote {

	void startGame() throws RemoteException;

	void yourTurn() throws RemoteException;

	void synchronizeModel(GomokuModel model) throws RemoteException;

	void receiveSessionId(int id) throws RemoteException;

	void endGame(boolean winner) throws RemoteException;

}
