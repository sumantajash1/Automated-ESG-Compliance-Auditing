import React, { useState } from "react";
import "./signup.css";
import { Link } from "react-router-dom";

const Signup = () => {
  const [formData, setFormData] = useState({
    gst: "",
    supplierName: "",
    contact: "",
    email: "",
    password: "",
    confirmPassword: "",
    role: ""
  });

  const [error, setError] = useState("");

  const handleChange = (e) => {
    setFormData((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (formData.password !== formData.confirmPassword) {
      setError("Passwords do not match.");
      return;
    }

    setError("");
    // TODO: Submit to backend
    console.log("Form submitted:", formData);
  };

  return (
    <div className="addUser">
      <h3>Sign Up</h3>
      <form className="addUserForm" onSubmit={handleSubmit}>
        <div className="inputGroup">
          <label htmlFor="gst">GST Number:</label>
          <input
            type="text"
            id="gst"
            name="gst"
            onChange={handleChange}
            value={formData.gst}
            placeholder="Enter GST Number"
          />

          <label htmlFor="supplierName">Supplier Name:</label>
          <input
            type="text"
            id="supplierName"
            name="supplierName"
            onChange={handleChange}
            value={formData.supplierName}
            placeholder="Enter Supplier Name"
          />

          <label htmlFor="contact">Contact Number:</label>
          <input
            type="tel"
            id="contact"
            name="contact"
            onChange={handleChange}
            value={formData.contact}
            placeholder="Enter Contact Number"
          />

          <label htmlFor="email">Email:</label>
          <input
            type="email"
            id="email"
            name="email"
            onChange={handleChange}
            value={formData.email}
            placeholder="Enter your Email"
          />

          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            name="password"
            onChange={handleChange}
            value={formData.password}
            placeholder="Enter Password"
          />

          <label htmlFor="confirmPassword">Confirm Password:</label>
          <input
            type="password"
            id="confirmPassword"
            name="confirmPassword"
            onChange={handleChange}
            value={formData.confirmPassword}
            placeholder="Re-enter Password"
          />

          <label htmlFor="role">Role:</label>
          <select id="role" name="role" onChange={handleChange} value={formData.role}>
            <option value="">Select Role</option>
            <option value="supplier">Supplier</option>
            <option value="auditor">Auditor</option>
            <option value="admin">Admin</option>
          </select>

          {error && <p className="error">{error}</p>}

          <button type="submit" className="btn btn-success">
            Sign Up
          </button>
        </div>
      </form>
      <div className="login">
        <p>Already have an Account?</p>
        <Link to="/login" className="login-link">
          Login
        </Link>
      </div>
    </div>
  );
};

export default Signup;
