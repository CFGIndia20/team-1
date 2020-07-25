import firebase from 'firebase/app';
import 'firebase/firestore';
import 'firebase/auth';

const config = {
    apiKey: "AIzaSyDZKPNKXkW_R8yUog4onZNm1VDVQuwTPcc",
    authDomain: "aashray-3d07c.firebaseapp.com",
    databaseURL: "https://aashray-3d07c.firebaseio.com",
    projectId: "aashray-3d07c",
    storageBucket: "aashray-3d07c.appspot.com",
    messagingSenderId: "417279724498",
    appId: "1:417279724498:web:66fc11b9ab02c5dbee560b",
    measurementId: "G-WHC1XZ7MFQ"

};

firebase.initializeApp(config);

export const provider = new firebase.auth.GoogleAuthProvider();

export default firebase;