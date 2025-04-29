// import "./App.css";
import "./components/signup.css";

import { Toaster } from "@/components/ui/toaster";
import { Toaster as Sonner } from "@/components/ui/sonner";
import { TooltipProvider } from "@/components/ui/tooltip";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { BrowserRouter, Routes, Route, useLocation } from "react-router-dom";
import Index from "./pages/Index";
import SupplierUpload from "./pages/SupplierUpload";
import NotFound from "./pages/NotFound";

import Navbar from "./components/Navbar";
import LoginPage from "./components/LoginPage";
import SignupPage from "./components/Signup";
import HomePage from "./components/Home";
import AboutPage from "./components/About";
import WorkPage from "./components/Work";
import ContactPage from "./components/Contact";
import Footer from "./components/Footer";
import { MapPicker } from "./components/MapPicker.jsx";

const queryClient = new QueryClient();

// AppWrapper handles conditional layout based on route
function AppWrapper() {
  const location = useLocation();
  const hideNavbarPaths = ["/login", "/signup"];

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

        <Route path="/needToCheck" element={<Index />} />
        <Route path="/supplier-upload" element={<SupplierUpload />} />
        {/* ADD ALL CUSTOM ROUTES ABOVE THE CATCH-ALL "*" ROUTE */}

        <Route path="/map" element={<MapPicker />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </>
  );
}

const App = () => (
  <QueryClientProvider client={queryClient}>
    {/* <TooltipProvider> */}
      <Toaster />
      <Sonner />
      <BrowserRouter>
        <AppWrapper />
      </BrowserRouter>
    {/* </TooltipProvider> */}
  </QueryClientProvider>
);

export default App;
