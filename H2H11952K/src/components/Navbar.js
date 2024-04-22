import React from 'react';
import './Navbar.css';

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-left">
        <a href="/" className="navbar-link">
          HOMEPAGE
        </a>
        <a href="/add-data" className="navbar-link">
          ADD DATA
        </a>
        <a href="/analytics-view" className="navbar-link">
          ANALYTICS VIEW
        </a>
      </div>
      <div className="navbar-right">
        <input
          type="text"
          placeholder="Search Customer Order Id"
          className="search-bar"
        />
        <button className="advanced-search-button">Advanced Search</button>
      </div>
    </nav>
  );
};
export default Navbar;