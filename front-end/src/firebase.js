// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries
import { getAuth } from "firebase/auth";

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyAGjtobb9sBaoNYJ8631jsv8xoLudHeaBA",
  authDomain: "dep11-chat.firebaseapp.com",
  projectId: "dep11-chat",
  storageBucket: "dep11-chat.appspot.com",
  messagingSenderId: "882160465630",
  appId: "1:882160465630:web:27c5743888547d0fb6b44f"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

// Initialize Firebase Authentication and get a reference to the service
const auth = getAuth(app);
export {app, auth};