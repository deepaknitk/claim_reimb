import React, {Component} from 'react';
import Axios from 'axios';
import './styles.scss';
import TableLayout from '../../components/Table';
import ManageClaims from '../ManageClaims';
import Link from 'react-router-dom';

class ClaimHistory extends Component {
    constructor(props, context) {
        super(props, context);
        this.state = {
            claims: [],
            claimsByRembId: [],
            openClaims: 0,
            closedClaims: 0,
            rejectedClaims: 0,
            remID: null,
            remIdForClaim: null

        };
        this.getTableHeader = this.getTableHeader.bind(this);
        this.getTableBody = this.getTableBody.bind(this);
    }

    getRembDetails(claimDetails) {
        const tempRemId = claimDetails.reimbursementId;
        console.log(tempRemId);
        this.setState({remID: tempRemId});
        Axios
            .get('/claims/rmbItem/findRmbItemById', {
                params: {
                    rmbItemId: claimDetails.reimbursementId
                  }
            })
            .then(response => {
                if (response.data.success) {
                    this.setState({claimsByRembId: response.data.data});
                    this.props.history.push({
                            pathname: '/manageClaims',
                            state: { some: this.state.claimsByRembId, remIdForClaim: this.state.remID}
                          });
                }
            })
            .catch(() => {
                console.log('fail');
            });

    }


    getTableHeader() {
        return (
            <tr className="table_layout table_header">
                <th>SN.</th>
                <th>ReimbursementId</th>
                <th>Reimbursement Date</th>
                <th>statusCode</th>
                <th>Action</th>
            </tr>
        );
    }

    getTableBody() {
        return (
            <tbody className="table_body">
                {this.state.claims.map((claimDetails, i) => <tr key={i}>
                <td>{i + 1}</td>
                <td>{claimDetails.reimbursementId}</td>
                <td>{new Date(claimDetails.reimbursement_date).toLocaleDateString()}</td>
                <td>{claimDetails.statusCode}</td>
                <button type="button" className="btn btn-info m-t-5" onClick={() => this.getRembDetails(claimDetails)} >View Details</button>
            </tr>)}
            </tbody>
        );
    }

    componentDidMount() {
        let user = JSON.parse(localStorage.getItem('user'));
        let email = user.email;
        Axios
            .get('/claims/api/reimbursement/findAll', {
                params: {
                    'userId': email
                  }
            })
            .then(response => {
                if (response.data.success) {
                    this.setState({claims: response.data.data});
                    let open = 0;
                    let closed = 0;
                    let rejected = 0;
                    response.data.data.map(claim=>{
                        if(claim.statusCode === 'OPEN') {
                            open++;
                        }
                        if(claim.statusCode === 'CLOSED') {
                            closed++;
                        }
                        if(claim.statusCode === 'REJECTED') {
                            rejected++;
                        }
                    });
                    this.setState({openClaims: open, closedClaims: closed, rejectedClaims: rejected});
                }
            })
            .catch(() => {
                console.log('fail');
            });
    }

    render() {
        return (
            <div style={{margin: '50px'}}>
                <div className="card-deck">
                    <div className="card bg-primary p-30 claimHistoryHeader">
                        <h4 className="">Open Reimbursement</h4>
                        <div className="card-body text-center">
                           <h2>{this.state.openClaims}</h2>
                        </div>
                    </div>
                    <div className="card bg-success p-30 claimHistoryHeader">
                        <h4>Closed Reimbursement</h4>
                        <div className="card-body text-center">
                            <h2>{this.state.closedClaims}</h2>
                        </div>
                    </div>
                    <div className="card bg-warning p-30 claimHistoryHeader">
                        <h4>Rejected Reimbursement</h4>
                        <div className="card-body text-center">
                            <h2>{this.state.rejectedClaims}</h2>
                        </div>
                    </div>
                </div>

                <h2 className="text-center m-t-20 m-b-20 card p-10" >Reimbursement List</h2>
                <div className="table-container">
                    <TableLayout
                        cssClassName="table_layout table_header"
                        getTableHeader={this.getTableHeader}
                        getTableBody={this.getTableBody}
                     />
                </div>
            </div>


        );
    }
}
export default ClaimHistory;
