import './App.css';
import './components/signup.css';

import { BrowserRouter, Routes, Route, useLocation } from 'react-router-dom';
import Navbar from './components/Navbar';
import LoginPage from './components/LoginPage';
import SignupPage from './components/Signup';
import HomePage from './components/Home';
import AboutPage from './components/About';
import WorkPage from './components/Work';
import ContactPage from './components/Contact';
import Footer from "./components/Footer";

// AppWrapper handles conditional layout based on route
function AppWrapper() {
  const location = useLocation();
  const hideNavbarPaths = ['/login', '/signup'];

  return (
    <>
      {/* Conditionally render Navbar */}
      {!hideNavbarPaths.includes(location.pathname) && <Navbar />}

      <Routes>
        {/* Landing page content */}
        <Route
          path="/"
          element={
            <>
              <HomePage />
              <AboutPage />
              <WorkPage />
              <ContactPage />
              <Footer />
            </>
          }
        />

        {/* Auth Pages */}
        <Route path="/login" element={<LoginPage />} />
        <Route path="/signup" element={<SignupPage />} />
      </Routes>
    </>
  );
}

// Main App component
function App() {
  return (
    <BrowserRouter>
      <AppWrapper />
    </BrowserRouter>
  );
}

export default App;
