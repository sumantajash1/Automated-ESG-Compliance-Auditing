import React from "react";
import Logo from "../Assets/Logo.png";
import { BsTwitter } from "react-icons/bs";
import { SiLinkedin } from "react-icons/si";
import { BsYoutube } from "react-icons/bs";
import { FaFacebookF } from "react-icons/fa";

const Footer = () => {
  return (
    <footer className="bg-white py-12 px-8 md:px-16">
      <div className="max-w-6xl mx-auto flex flex-col md:flex-row justify-between">
        {/* Section One - Logo and Social Icons */}
        <div className="mb-8 md:mb-0">
          <div className="mb-6">
            <img src={Logo} alt="Company Logo" className="h-12" />
          </div>
          <div className="flex space-x-4">
            <a href="#" className="text-gray-600 hover:text-blue-500 text-xl">
              <BsTwitter />
            </a>
            <a href="#" className="text-gray-600 hover:text-blue-700 text-xl">
              <SiLinkedin />
            </a>
            <a href="#" className="text-gray-600 hover:text-red-600 text-xl">
              <BsYoutube />
            </a>
            <a href="#" className="text-gray-600 hover:text-blue-600 text-xl">
              <FaFacebookF />
            </a>
          </div>
        </div>

        {/* Section Two - Footer Links */}
        <div className="grid grid-cols-1 sm:grid-cols-3 gap-8">
          {/* Column 1 */}
          <div className="flex flex-col space-y-3">
            <span className="text-gray-800 hover:text-blue-600 cursor-pointer">Quality</span>
            <span className="text-gray-800 hover:text-blue-600 cursor-pointer">Help</span>
            <span className="text-gray-800 hover:text-blue-600 cursor-pointer">Share</span>
            <span className="text-gray-800 hover:text-blue-600 cursor-pointer">Careers</span>
            <span className="text-gray-800 hover:text-blue-600 cursor-pointer">Testimonials</span>
            <span className="text-gray-800 hover:text-blue-600 cursor-pointer">Work</span>
          </div>

          {/* Column 2 */}
          <div className="flex flex-col space-y-3">
            <span className="text-gray-800 hover:text-blue-600 cursor-pointer">244-5333-7783</span>
            <span className="text-gray-800 hover:text-blue-600 cursor-pointer">hello@food.com</span>
            <span className="text-gray-800 hover:text-blue-600 cursor-pointer">press@food.com</span>
            <span className="text-gray-800 hover:text-blue-600 cursor-pointer">contact@food.com</span>
          </div>

          {/* Column 3 */}
          <div className="flex flex-col space-y-3">
            <span className="text-gray-800 hover:text-blue-600 cursor-pointer">Terms & Conditions</span>
            <span className="text-gray-800 hover:text-blue-600 cursor-pointer">Privacy Policy</span>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;