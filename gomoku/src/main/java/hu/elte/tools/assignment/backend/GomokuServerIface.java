package hu.elte.tools.assignment.backend;

import hu.elte.tools.assignment.shared.model.GomokuModel;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by cmwal on 2017. 08. 11..
 */
public interface GomokuServerIface extends Remote{

	boolean login(String userName, String password) throws RemoteException;

	boolean register(String userName,  String password) throws RemoteException;

	void searchForMatch(String userName, String address) throws RemoteException;

	void synchronizeModel(GomokuModel model) throws RemoteException;

}
