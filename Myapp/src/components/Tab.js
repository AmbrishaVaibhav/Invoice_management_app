import React, { useState } from 'react';
import Datagrid from './DataGrid';
import './tabs.css'; // Import the CSS file for the TabPage component
import AddData from './AddData';

const TabPage = () => {
    const [activeTab, setActiveTab] = useState('home');
    const [isFormVisible, setFormVisible] = useState(false); // State to track form visibility

    const handleTabChange = (tab) => {
        setActiveTab(tab);
    };

    const handleSearch = () => {
        setFormVisible(!isFormVisible); // Toggle the form visibility on search button click
    };


    return (
        <div>
            <nav className="navbar">
                <ul className="nav-links">
                    <li
                        className={`nav-link ${activeTab === 'home' ? 'active' : ''}`}
                        onClick={() => handleTabChange('home')}
                    >
                        Home
                    </li>
                    <li
                        className={`nav-link ${activeTab === 'addData' ? 'active' : ''}`}
                        onClick={() => handleTabChange('addData')}
                    >
                        Add Data
                    </li>
                    <li
                        className={`nav-link ${activeTab === 'analytics' ? 'active' : ''}`}
                        onClick={() => handleTabChange('analytics')}
                    >
                        Analytics View
                    </li>
                    <li className="search-container">
                        <input type="text" placeholder="Search Customer Order Id" className="search-field" />

                        <button className="search-button" onClick={handleSearch}>ADVANCED SEARCH</button>
                    </li>
                </ul>
            </nav>
            <div className="tab-content">
                {activeTab === 'home' && <Datagrid />}
                {activeTab === 'addData' && <AddData/>}
                {activeTab === 'analytics' && <div>Analytics View Content</div>}
            </div>
            {isFormVisible && (
                <div className="popup-form">
                    <h3>Advanced Search</h3>
          <div className='input-container'>
            <div>
          <input type="text" placeholder="Customer Order Id" className="coi" />
          </div>
          <div>
          <input type="text" placeholder="Customer number" className="cn" />
          </div>
          <div>
          <input type="text" placeholder="Sales Org" className="so" />
          </div>
          </div>
          <div className='button-container'>
            <button className='search-button1'>Search</button>
            <button className='cancel-button'>Cancel</button>
          </div>
                </div>
            )}

        </div>
    );
};

export default TabPage;