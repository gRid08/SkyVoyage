import axios from "axios"
import { getUserData} from './storage'
import { initializeApp } from 'firebase/app';
import { getDatabase } from 'firebase/database'
import { getAuth } from 'firebase/auth';
import { getFirestore } from 'firebase/firestore';

axios.defaults.baseURL = "https://identitytoolkit.googleapis.com/v1";
const API_KEY = "AIzaSyDOAIpSKqsbsTFRdhb09uMo4_hkgQ7KJYQ"
const REGISTER_URL = `/accounts:signUp?key=${API_KEY}`;
const LOGIN_URL = `/accounts:signInWithPassword?key=${API_KEY}`;
const USER_DETAILS_URL = `/accounts:lookup?key=${API_KEY}`;

const firebaseConfig = {
  apiKey: "AIzaSyAD7qa3AqBki3WfS0M0EV68oggCJgTKYNo",
  authDomain: "skyvoyage-67541.firebaseapp.com",
  databaseURL: "https://skyvoyage-67541-default-rtdb.firebaseio.com",
  projectId: "skyvoyage-67541",
  storageBucket: "skyvoyage-67541.appspot.com",
  messagingSenderId: "848831090716",
  appId: "1:848831090716:web:3dbe296b252c30a426cfd9",
  measurementId: "G-DEWJ9FE4F0"
};


export const RegisterApi = (inputs)=>{
    let data  = {displayName:inputs.name,email:inputs.email,password:inputs.password }
    return axios.post(REGISTER_URL,data)
}
export const LoginApi = (inputs)=>{
    let data  = {email:inputs.email,password:inputs.password }
    return axios.post(LOGIN_URL,data)
}
export const UserDetailsApi = ()=>{
    let data = {idToken:getUserData()}
    return axios.post(USER_DETAILS_URL,data)
}

const app = initializeApp(firebaseConfig);
export const database = getDatabase(app);
export const firestore = getFirestore(app);
export { app };
// Export the Firebase database instance

