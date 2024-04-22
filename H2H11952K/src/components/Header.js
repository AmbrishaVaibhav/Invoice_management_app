import React from 'react';
import './header.css';
import hrclogo from './hrclogo.svg';
import abclogo from './abclogo.svg';
const Header = () => {
    return (
        <div className='header'>
            <div className='logos'>
                <div className='abc'>
                    <img src={abclogo} alt="abclogo " style={{height: '30px'}}/>
                </div>
                <div className='hrc'>
                    <img src={hrclogo} alt="hrclogo" style={{height: '30px'}}/>
                </div>
            </div>
            <div className='Invoice'>
                <h3>Invoice List</h3>
            </div>
        </div>
    );
};

export default Header;