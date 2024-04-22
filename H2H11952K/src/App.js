import Datagrid from './components/DataGrid';
import Header from './components/Header';
import Navbar from './components/Navbar.js';
import Footer from './components/Footer.js';
import TabPage from './components/Tab';
import './App.css'

function App() {
  return (
    <div className='page'>
      <div className='header'>
        <Header />
      </div>
        <div>
        <TabPage />
        </div>
      <div className='Footer'>
        <Footer />
      </div>
    </div>
  );
}

export default App;
