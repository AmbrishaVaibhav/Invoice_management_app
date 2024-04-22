import React, { useEffect, useState } from 'react';
import { DataGrid,GridPagination } from '@mui/x-data-grid';
import axios from 'axios';
import './DataGrid.css';
import Button from '@material-ui/core/Button';

function Datagrid() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedRow, setSelectedRow] = useState([]);

  const getInv = async () => {
    try {
      const response = await axios.get(
        'http://localhost:9000/h2h_milestone3/ReadInvoice'
      );
      setData(response.data);
      setLoading(false);
    } catch (error) {
      setError(error);
      setLoading(false);
    }
  };

  useEffect(() => {
    getInv();
  }, []);

  useEffect(() => {
    console.log(data);
  }, [data]);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error fetching invoice data: {error.message}</div>;
  }

  const columns = [
    { field: 'slNo', headerName: 'Sl No', width: 60 },
    { field: 'customerOrderId', headerName: 'CUSTOMER\nORDER ID', width: 180 },
    { field: 'salesOrg', headerName: 'SALES ORG', width: 120 },
    { field: 'distributionChannel', headerName: 'DISTRIBUTION\nCHANNEL', width: 220 },
    { field: 'orderCreationDate', headerName: 'ORDER CREATION DATE', width: 190 },
    { field: 'orderAmount', headerName: 'ORDER\nAMOUNT', width: 150 },
    { field: 'orderCurrency', headerName: 'ORDER\nCURRENCY', width: 180 },
    { field: 'customerNumber', headerName: 'CUSTOMER NUMBER', width: 180 },
    { field: 'amountInUSD', headerName: 'AMOUNT IN USD', width: 150 },
  ];

  const rows = data
    ? data.map((row, index) => ({
        id: index + 1,
        slNo: row.slNo,
        customerOrderId: row.customerOrderId,
        salesOrg: row.salesOrg,
        distributionChannel: row.distributionChannel,
        orderCreationDate: row.orderCreationDate,
        orderAmount: row.orderAmount,
        orderCurrency: row.orderCurrency,
        customerNumber: row.customerNumber,
        amountInUSD: row.amountInUSD,
      }))
    : [];
    const handleRefresh = () => {
      console.log('REFRESH DATA clicked');
    };
  
    const handleEdit = () => {
      console.log('EDIT clicked');
      // Handle edit logic
    };
  
    const handleDelete = () => {
      console.log('DELETE clicked');
      // Handle delete logic
    };
  
    const handlePredict = () => {
      console.log('PREDICT clicked');
      // Handle predict logic
    };
    const CustomFooter = () => (
        <div className="datagrid-footer">
          <div className="button-container">
            <Button className="datagrid-button" onClick={handleRefresh} style={{ backgroundColor: '#fc7500' }}>
              REFRESH DATA
            </Button>
            <Button
              className="datagrid-button"
              onClick={handleEdit}
              style={{ backgroundColor: '#fc7500' }}
            >
              EDIT
            </Button>
            <Button className="datagrid-button" onClick={handleDelete} style={{ backgroundColor: '#fc7500' }}>
              DELETE
            </Button>
            <Button className="datagrid-button" onClick={handlePredict} style={{ backgroundColor: '#fc7500' }}>
              PREDICT
            </Button>
          </div>
          <GridPagination
            componentProps={{
              pageSizeOptions: [5, 10, 25, 50, 100],
              rowsPerPageText: 'Rows per page:',
            }}
            RowsPerPageMenuProps={{
              anchorOrigin: {
                vertical: 'bottom',
                horizontal: 'left',
              },
              transformOrigin: {
                vertical: 'top',
                horizontal: 'left',
              },
              getContentAnchorEl: null,
            }}
          />
        </div>
      );
      
  return (
    <div style={{ width: '100%' }}>
      <DataGrid
        className="DG"
        rows={rows}
        columns={columns}
        checkboxSelection
        disableRowSelectionOnClick
        pageSize={5}
        components={{
          Footer: CustomFooter,
        }}
      />
    </div>
  );
}

export default Datagrid;
