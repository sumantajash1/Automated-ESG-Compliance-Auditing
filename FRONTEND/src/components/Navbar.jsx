import React, { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom';
import Logo from "../Assets/Logo.png"
import { BsCart2 } from "react-icons/bs";
import { HiOutlineBars3 } from "react-icons/hi2";
import Box from "@mui/material/Box";
import Drawer from "@mui/material/Drawer";
import List from "@mui/material/List";
import Divider from "@mui/material/Divider";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import HomeIcon from "@mui/icons-material/Home";
import InfoIcon from "@mui/icons-material/Info";
import CommentRoundedIcon from "@mui/icons-material/CommentRounded";
import PhoneRoundedIcon from "@mui/icons-material/PhoneRounded";
import ShoppingCartRoundedIcon from "@mui/icons-material/ShoppingCartRounded";

function Navbar() {
  const [openMenu, setOpenMenu] = useState(false);
  const navigate = useNavigate(); // Initialize the navigate function
  
  const menuOptions = [
    {
      text: "Home",
      icon: <HomeIcon />,
      path: "/"
    },
    {
      text: "About",
      icon: <InfoIcon />,
      path: "/about"
    },
    {
      text: "Work",
      icon: <CommentRoundedIcon />,
      path: "/work"
    },
    {
      text: "Contact",
      icon: <PhoneRoundedIcon />,
      path: "/contact"
    },
  ];

  // Function to handle navigation
  const handleNavigation = (path) => {
    navigate(path);
    setOpenMenu(false);
  };

  return (
    <nav>
      <div className="nav-logo-container">
        <img src={Logo} alt=""
          style={{
            marginLeft: "5vw",
            height: "5.5vw",
            width: "18vw"
          }}
        />
      </div>
      <div className="navbar-links-container">
        <Link to="/">Home</Link>
        <Link to="/about">About</Link>
        <Link to="/work">Work</Link>
        <Link to="/contact">Contact</Link>
        
        <button className="primary-button m-px" onClick={() => navigate('/login')}>Login</button>
        <button className="primary-button" onClick={() => navigate('/signup')}>Sign Up</button>
      </div>
      <div className="navbar-menu-container">
        <HiOutlineBars3 onClick={() => setOpenMenu(true)} />
      </div>
      <Drawer open={openMenu} onClose={() => setOpenMenu(false)} anchor="right">
        <Box
          sx={{ width: 250 }}
          role="presentation"
          onClick={() => setOpenMenu(false)}
          onKeyDown={() => setOpenMenu(false)}
        >
          <List>
            {menuOptions.map((item) => (
              <ListItem key={item.text} disablePadding>
                <ListItemButton onClick={() => handleNavigation(item.path)}>
                  <ListItemIcon>{item.icon}</ListItemIcon>
                  <ListItemText primary={item.text} />
                </ListItemButton>
              </ListItem>
            ))}
            <ListItem disablePadding>
              <ListItemButton onClick={() => handleNavigation('/login')}>
                <ListItemIcon><HomeIcon /></ListItemIcon>
                <ListItemText primary="Login" />
              </ListItemButton>
            </ListItem>
            <ListItem disablePadding>
              <ListItemButton onClick={() => handleNavigation('/signup')}>
                <ListItemIcon><HomeIcon /></ListItemIcon>
                <ListItemText primary="Sign Up" />
              </ListItemButton>
            </ListItem>
          </List>
          <Divider />
        </Box>
      </Drawer>
    </nav>
  );
}

export default Navbar