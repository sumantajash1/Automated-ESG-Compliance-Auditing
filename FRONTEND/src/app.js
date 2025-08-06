"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
// import "./App.css";
require("./components/signup.css");
var toaster_1 = require("@/components/ui/toaster");
var sonner_1 = require("@/components/ui/sonner");
var react_query_1 = require("@tanstack/react-query");
var react_router_dom_1 = require("react-router-dom");
var Index_1 = require("./pages/Index");
var SupplierUpload_1 = require("./pages/SupplierUpload");
var NotFound_1 = require("./pages/NotFound");
var Navbar_1 = require("./components/Navbar");
var LoginPage_1 = require("./components/LoginPage");
var Signup_1 = require("./components/Signup");
var Home_1 = require("./components/Home");
var About_1 = require("./components/About");
var Work_1 = require("./components/Work");
var Contact_1 = require("./components/Contact");
var Footer_1 = require("./components/Footer");
var MapPicker_jsx_1 = require("./components/MapPicker.jsx");
var queryClient = new react_query_1.QueryClient();
// AppWrapper handles conditional layout based on route
function AppWrapper() {
    var location = (0, react_router_dom_1.useLocation)();
    var hideNavbarPaths = ["/login", "/signup"];
    return (<>
      {/* Conditionally render Navbar */}
      {!hideNavbarPaths.includes(location.pathname) && <Navbar_1.default />}

      <react_router_dom_1.Routes>
        {/* Landing page content */}
        <react_router_dom_1.Route path="/" element={<>
              <Home_1.default />
              <About_1.default />
              <Work_1.default />
              <Contact_1.default />
              <Footer_1.default />
            </>}/>

        {/* Auth Pages */}
        <react_router_dom_1.Route path="/login" element={<LoginPage_1.default />}/>
        <react_router_dom_1.Route path="/signup" element={<Signup_1.default />}/>

        <react_router_dom_1.Route path="/needToCheck" element={<Index_1.default />}/>
        <react_router_dom_1.Route path="/supplier-upload" element={<SupplierUpload_1.default />}/>
        {/* ADD ALL CUSTOM ROUTES ABOVE THE CATCH-ALL "*" ROUTE */}

        <react_router_dom_1.Route path="/map" element={<MapPicker_jsx_1.MapPicker />}/>
        <react_router_dom_1.Route path="*" element={<NotFound_1.default />}/>
      </react_router_dom_1.Routes>
    </>);
}
var App = function () { return (<react_query_1.QueryClientProvider client={queryClient}>
    {/* <TooltipProvider> */}
      <toaster_1.Toaster />
      <sonner_1.Toaster />
      <react_router_dom_1.BrowserRouter>
        <AppWrapper />
      </react_router_dom_1.BrowserRouter>
    {/* </TooltipProvider> */}
  </react_query_1.QueryClientProvider>); };
exports.default = App;
