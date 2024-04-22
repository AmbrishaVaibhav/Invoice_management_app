import React, { useState } from 'react';
import './AddData.css'

function AddData() {
  const [customerOrderId, setCustomerOrderId] = useState('');
  const [salesOrg, setSalesOrg] = useState('');
  const [distributionChannel, setDistributionChannel] = useState('');
  const [customerNumber, setCustomerNumber] = useState('');
  const [companyCode, setCompanyCode] = useState('');
  const [orderCurrency, setOrderCurrency] = useState('');
  const [amountInUSD, setAmountInUSD] = useState('');
  const [orderCreationDate, setOrderCreationDate] = useState('');

  const handleAdd = () => {
    // Logic to handle adding data
    // You can access the entered values using the corresponding state variables
    console.log('Add button clicked');
    console.log('Customer Order ID:', customerOrderId);
    console.log('Sales Org:', salesOrg);
    console.log('Distribution Channel:', distributionChannel);
    console.log('Customer Number:', customerNumber);
    console.log('Company Code:', companyCode);
    console.log('Order Currency:', orderCurrency);
    console.log('Amount in USD:', amountInUSD);
    console.log('Order Creation Date:', orderCreationDate);
  };

  const handleClear = () => {
    // Logic to clear the entered data
    setCustomerOrderId('');
    setSalesOrg('');
    setDistributionChannel('');
    setCustomerNumber('');
    setCompanyCode('');
    setOrderCurrency('');
    setAmountInUSD('');
    setOrderCreationDate('');
  };

  return (
    <div>
     
      <div className='r'>
      <div className='r1'>
      <input
        type="text"
        className="customerOrderId"
        value={customerOrderId}
        placeholder='Customer Order Id'
        onChange={(e) => setCustomerOrderId(e.target.value)}
      />

      <input
        type="text"
        className="salesOrg"
        value={salesOrg}
        placeholder='Sales Org'
        onChange={(e) => setSalesOrg(e.target.value)}
      />
      <input
        type="text"
        className="distributionChannel"
        value={distributionChannel}
        placeholder='Distribution Channel'
        onChange={(e) => setDistributionChannel(e.target.value)}
      />
      </div>

    <div className="r2">
      <input
        type="text"
        className="customerNumber"
        value={customerNumber}
        placeholder='Customer Number'
        onChange={(e) => setCustomerNumber(e.target.value)}
      />

    
      <input
        type="text"
        className="companyCode"
        value={companyCode}
        placeholder='Company code'
        onChange={(e) => setCompanyCode(e.target.value)}
      />
      <input
        type="text"
        className="orderCurrency"
        value={orderCurrency}
        placeholder='Order Currency'
        onChange={(e) => setOrderCurrency(e.target.value)}
      />
      <input
        type="text"
        className="amountInUSD"
        value={amountInUSD}
        placeholder='Amount in usd'
        onChange={(e) => setAmountInUSD(e.target.value)}
      />
      <input
        type="text"
        className="orderCreationDate"
        value={orderCreationDate}
        placeholder='Order Creation Date'
        onChange={(e) => setOrderCreationDate(e.target.value)}
      />
      </div>
  <div className='r3'>
      <button onClick={handleAdd} className='b1'>ADD</button>
      <button onClick={handleClear} className='b2'>CLEAR DATA</button>
    </div>
    </div>
</div>
  );
}

export default AddData;
