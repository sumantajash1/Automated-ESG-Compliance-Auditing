import React from "react";
import "./login.css";
import { Link } from "react-router-dom";

const Login = () => {
  return (
    <div className="addUser">
      <h3>Sign in</h3>
      <form className="addUserForm">
        <div className="inputGroup">
            
        <label htmlFor="gst">GST Number:</label>
          <input
            type="text"
            id="gst"
            name="gst"
            autoComplete="off"
            placeholder="Enter your GST Number"
          />
          <label htmlFor="email">Email:</label>
          <input
            type="email"
            id="email"
            name="email"
            autoComplete="off"
            placeholder="Enter your Email"
          />
          <label htmlFor="Password">Password:</label>
          <input
            type="password"
            id="password"
            name="password"
            autoComplete="off"
            placeholder="Enter your Password"
          />
          <button type="submit" class="btn btn-primary">
            Login
          </button>
        </div>
      </form>
      <div className="login">
        <p>Don't have Account? </p>
        <Link to="/signup" className="signup-link">
            Sign Up
        </Link>

      </div>
    </div>
  );
};

export default Login;