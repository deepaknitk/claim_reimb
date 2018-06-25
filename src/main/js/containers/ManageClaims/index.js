import React, {Component} from 'react';
import Axios from 'axios';
import './styles.scss';
import TableLayout from '../../components/Table';
import Button from '../../components/Button';

class ManageClaims extends Component {
    constructor(props, context) {
        super(props, context);
        this.state = {
            claims: [],
            openClaims: 0,
            closedClaims: 0,
            rejectedClaims: 0,
            varifiedClaims: 0,
            loggedInUseremail: null,
            updatedClaimsWithAction: null,
            tempRemId: null
        };
        this.getTableHeader = this.getTableHeader.bind(this);
        this.getTableBody = this.getTableBody.bind(this);
        this.radiochangeHandler = this.radiochangeHandler.bind(this);
        this.submitClaims = this.submitClaims.bind(this);
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
                <th>Status</th>
                { this.state.userEmail === 'foram.shah@coviam.com' || this.state.userEmail === 'deepak.mishra@coviam.com' ? <th>Approve</th> : '' }
                { this.state.userEmail === 'foram.shah@coviam.com' || this.state.userEmail === 'deepak.mishra@coviam.com' ? <th>Reject</th> : '' }
            </tr>
        );
    }

    radiochangeHandler(event, claimDetails, index) {
        let arr = [];
        claimDetails.action = event.target.value;
        arr.splice( index, 0, claimDetails );
        this.setState({updatedClaimsWithAction: arr});
    }


    getTableBody() {
        return (
            <tbody className="table_body">
            {this.state.claims.map((claimDetails, i) => <tr key={i}>
            <td>{i + 1}</td>
            <td>24/06/2018</td>
            <td>{claimDetails.rmbItemBillNumber}</td>
            <td>{claimDetails.rfqItemDescription}</td>
            <td>{claimDetails.expenseTypeDescription}</td>
            <td>{claimDetails.rmbItemAmount}</td>
            <td>{claimDetails.remarks}</td>
            <td>{claimDetails.rmbItemFilename}</td>
            <td style = {{fontSize: '18px', fontWeight: '600', color: 'blue'}}>{claimDetails.itemStatusCode}</td>
            { this.state.loggedInUseremail === 'foram.shah@coviam.com' || this.state.loggedInUseremail === 'deepak.mishra@coviam.com' ? <td>
            <input type="radio" name={i} value="APPROVED" onChange = {(event) => this.radiochangeHandler(event, claimDetails, i)} /> Approve
            </td> : ''}
           {this.state.loggedInUseremail === 'foram.shah@coviam.com' || this.state.loggedInUseremail === 'deepak.mishra@coviam.com' ? <td>
            <input type="radio" name={i} value="REJECTED" onChange = {(event)=>this.radiochangeHandler(event, claimDetails, i)} /> Reject
            </td> : ''}
        </tr>)}
        </tbody>
        );
    }

    componentDidMount() {
        let user = JSON.parse(localStorage.getItem('user'));
        let loggedInUseremail = user.email;
        this.setState({loggedInUseremail: loggedInUseremail});
        this.setState({claims: this.props.history.location.state.some, tempRemId: this.props.history.location.state.remIdForClaim});
        this.getList = this.getList.bind(this);
    }

    submitClaims() {
        const temp = this.state.updatedClaimsWithAction;
                let obj = {
                    reimbursementId: this.state.tempRemId,
                    userEmail: this.state.loggedInUseremail,
                    reimbursementItemStatusDtos: this.getList(temp)
                };
                Axios.post('/claims/manageClaims', obj)
                .then(response => {
                    if(response.data.success) {
                       this.props.history.push('/dashboard');
                    }
                }).catch(error=> {

                });
    }
    getList(temp) {
        let reimbursementItemStatusDtos = [];
       temp.map(claims => {
                let obj = {
                    'reimbursementItemId': claims.reimbursementItemId,
                    'remarks': claims.remarks,
                    'action': claims.action
                };
                reimbursementItemStatusDtos.push(obj);
       });
       return reimbursementItemStatusDtos;
    }

    render() {
        return (
            <div style={{margin: '50px'}}>
                {/* <div className="card-deck">
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

                    <div className="card bg-secondary card p-30 claimHistoryHeader">
                        <h4>Varified Claims</h4>
                        <div className="card-body text-center">
                            <h2>{this.state.rejectedClaims}</h2>
                        </div>
                    </div>
                </div> */}

                <h2 className="text-center m-t-20 m-b-20 card p-10" >Claim List</h2>

                <div className="table-container">
                    <TableLayout
                        cssClassName="table_layout table_header"
                        getTableHeader={this.getTableHeader}
                        getTableBody={this.getTableBody}
                     />
                </div>
                { this.state.loggedInUseremail === 'foram.shah@coviam.com' || this.state.loggedInUseremail === 'deepak.mishra@coviam.com' ? <div className="btn-container submit_form_container m-t-30">
                                <Button
                                    type="submit"
                                    cssClassName="m-r-10 p-l-30 p-r-30 btn-primary"
                                    buttonClickFunc={this.submitClaims}
                                    buttonName="Submit"
                                    isDisabled={this.props.formValidationFlag}/>
                            </div> : ''}
            </div>
        );
    }
}
export default ManageClaims;
