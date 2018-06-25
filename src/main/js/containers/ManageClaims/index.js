import React, {Component} from 'react';
import Axios from 'axios';
import './styles.scss';
import TableLayout from '../../components/Table';
import Button from '../../components/Button';

class ManageClaims extends Component {
    constructor(props, context) {
        super(props, context);
        this.state = {
            claims: [
                {
                    'itemStatusCode': 'OPEN',
                    'rmbItemBillNumber': '12',
                    'rfqItemDescription': 'rmb item desc 1',
                    'remarks': 'for one person',
                    'expenseTypeDescription': 'Food Expense Lunch',
                    'currencyCode': 'INR',
                    'rmbItemAmount': 200,
                    'rmbItemFilename': 'file.pdf',
                    'rmbDate': null
                },
                {
                    'itemStatusCode': 'OPEN',
                    'rmbItemBillNumber': '12',
                    'rfqItemDescription': 'rmb item desc 1',
                    'remarks': 'for one person',
                    'expenseTypeDescription': 'Food Expense Lunch',
                    'currencyCode': 'INR',
                    'rmbItemAmount': 200,
                    'rmbItemFilename': 'file.pdf',
                    'rmbDate': null
                },
                {
                    'itemStatusCode': 'OPEN',
                    'rmbItemBillNumber': '12',
                    'rfqItemDescription': 'rmb item desc 1',
                    'remarks': 'for one person',
                    'expenseTypeDescription': 'Food Expense Lunch',
                    'currencyCode': 'INR',
                    'rmbItemAmount': 200,
                    'rmbItemFilename': 'file.pdf',
                    'rmbDate': null
                }
            ],
            openClaims: 0,
            closedClaims: 0,
            rejectedClaims: 0,
            loggedInUseremail: null
        };
        this.getTableHeader = this.getTableHeader.bind(this);
        this.getTableBody = this.getTableBody.bind(this);
    }

    getTableHeader() {
        return (
            <tr className="table_layout table_header">
                <th>SN.</th>
                <th>Date</th>
                <th>Bill No.</th>
                <th>Discriptions</th>
                <th>Type</th>
                <th>Amount</th>
                <th>Remarks</th>
                <th>Image</th>
                { this.state.userEmail === 'vishnu@coviam.com' || this.state.userEmail === 'priya@coviam.com' ? <th>Approve</th> : '' }
                { this.state.userEmail === 'vishnu@coviam.com' || this.state.userEmail === 'priya@coviam.com' ? <th>Reject</th> : '' }

            </tr>
        );
    }

    getTableBody() {
        return (
            <tbody className="table_body">
            {this.state.claims.map((claimDetails, i) => <tr key={i}>
            <td>{i + 1}</td>
            <td>{new Date(claimDetails.reimbursement_date).toLocaleDateString()}</td>
            <td>{claimDetails.rmbItemBillNumber}</td>
            <td>{claimDetails.rfqItemDescription}</td>
            <td>{claimDetails.expenseTypeDescription}</td>
            <td>{claimDetails.rmbItemAmount}</td>
            <td>{claimDetails.remarks}</td>
            <td>{claimDetails.rmbItemFilename}</td>
            { this.state.loggedInUseremail === 'foram.shah@coviam.com' || this.state.loggedInUseremail === 'priya@coviam.com' ? <td>
            <input type="radio" name={i} value="male"/> Approve
            </td> : ''}
           { this.state.loggedInUseremail === 'foram.shah@coviam.com' || this.state.loggedInUseremail === 'priya@coviam.com' ? <td>
            <input type="radio" name={i} value="male"/> Reject
            </td> : ''}
        </tr>)}
        </tbody>
        );
    }

    componentDidMount() {
        let user = JSON.parse(localStorage.getItem('user'));
        let loggedInUseremail = user.email;
        this.setState({loggedInUseremail: loggedInUseremail});
        this.setState({claims: this.props.history.location.state.some});
    }

    render() {
        return (
            <div style={{margin: '50px'}}>
                <div className="card-deck">
                    <div className="card bg-primary p-30 claimHistoryHeader">
                        <h4 className="">Open Claims</h4>
                        <div className="card-body text-center">
                           <h2>{this.state.openClaims}</h2>
                        </div>
                    </div>
                    <div className="card bg-success p-30 claimHistoryHeader">
                        <h4>Closed Claims</h4>
                        <div className="card-body text-center">
                            <h2>{this.state.closedClaims}</h2>
                        </div>
                    </div>
                    <div className="card bg-warning p-30 claimHistoryHeader">
                        <h4>Rejected Claims</h4>
                        <div className="card-body text-center">
                            <h2>{this.state.rejectedClaims}</h2>
                        </div>
                    </div>
                </div>

                <h2 className="text-center m-t-20 m-b-20 card p-10" >Claim History</h2>

                <div className="table-container">
                    <TableLayout
                        cssClassName="table_layout table_header"
                        getTableHeader={this.getTableHeader}
                        getTableBody={this.getTableBody}
                     />
                </div>
                { this.state.loggedInUseremail === 'foram.shah@coviam.com' || this.state.loggedInUseremail === 'vishnu@coviam.com' ? <div className="btn-container submit_form_container m-t-30">
                                <Button
                                    type="submit"
                                    cssClassName="m-r-10 p-l-30 p-r-30 btn-primary"
                                    buttonClickFunc={this.validateformSubmission}
                                    buttonName="Submit"
                                    isDisabled={this.props.formValidationFlag}/>
                            </div> : ''}
            </div>


        );
    }
}
export default ManageClaims;
