import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';


class PriceList extends Component {

	constructor(props) {
    super(props);
    this.state = {prices: [], isLoading: true, profit: 0, buy: 0,sell: 0, currency: ''};
    //this.remove = this.remove.bind(this);
  }

  
   componentDidMount() {
		this.setState({isLoading: true});

		fetch('api/quotes')
		  .then(response => response.json())
		  .then(data => this.setState({prices: data, isLoading: false}));
	 }
    handleOnClick = (currency) => {
        // get data for all user choices (checkout axis or isomorphic-fetch)
        
        // start the fetching process (the CatTable will now display "Loading...")
        this.setState({
            fetching: true
        })

        const url = `http://localhost:8080/api/profits/${currency}`

        fetch(url)
.then(function (response) {
            return response.json();
    })
	.then(response => this.setState(
	{
		profit: response.profit,
		buy: response.buy,
		sell: response.sell,
		currency: response.currency
	}));
	

    }


	
  
  render() {
    const {prices, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const priceList = prices.map(price => {
   	    return <tr key={price.id}>
			<td style={{whiteSpace: 'nowrap'}}>{price.currency}</td>
			<td>{new Intl.DateTimeFormat('en-GB', {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit'
          }).format(new Date(price.date))}</td>
		  
		  
	
	
			<td>{price.quote.map(quoteitem => {
			return <div key={quoteitem.id}> {quoteitem.time}  </div>})}</td>
			
			<td>{price.quote.map(quoteitem => {
			return <div key={quoteitem.id}> {quoteitem.price} </div>})}</td>
		
			
			<td>
          
            
            <Button size="sm" color="danger" onClick={() => this.handleOnClick(price.currency)}>Profit</Button>
	<ProfitItem elem={price.currency} currency={this.state.currency} buy={this.state.buy} sell={this.state.sell} profit={this.state.profit} />
		  
        </td>
      </tr>
	});
  
    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <h3>Crypto Historical Quotes</h3>
          <Table className="mt-4">
            <thead>
            <tr>
              <th width="20%">Currency</th>
              <th width="20%">Date</th>
			  <th width="20%">Time</th>
			  <th width="20%">Price</th>
              <th width="20%">Actions</th>
            </tr>
            </thead>
            <tbody>
            {priceList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default PriceList;

export function ProfitItem(props) {
	if(props.currency ===props.elem){
	return (<div>
			Buy : ${props.buy} <br/>
			Sell : ${props.sell} <br/>
			Profit : ${props.profit.toLocaleString(navigator.language, { minimumFractionDigits: 2 })}  <br/>
			</div>);
	} 
	return '';
}
