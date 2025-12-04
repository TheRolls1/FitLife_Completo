import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";

const firebaseConfig = {
  apiKey: "AIzaSyAW8UonI3M-u1Ux4zuEe9AJwd6hA2yYH_w",
  authDomain: "fitlife-1ddef.firebaseapp.com",
  projectId: "fitlife-1ddef",
  storageBucket: "fitlife-1ddef.appspot.com", // CORREGIDO
  messagingSenderId: "788737512638",
  appId: "1:788737512638:web:9c49e91c7476042f853aef",
  measurementId: "G-Y4M4B4CQ1G"
};

const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);

export { app, analytics };

