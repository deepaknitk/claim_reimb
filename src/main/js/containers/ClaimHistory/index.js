import React, {Component} from 'react';
import Axios from 'axios';
import './styles.scss';
import TableLayout from '../../components/Table';

class ClaimHistory extends Component {
    constructor(props, context) {
        super(props, context);
        this.state = {
            claims: []
        };
        this.getTableHeader = this.getTableHeader.bind(this);
        this.getTableBody = this.getTableBody.bind(this);
    }


    getTableHeader() {
        return (
            <tr className="table_layout table_header">
                <th></th>
                <th>Date</th>
                <th>Bill No.</th>
                <th>Discriptions</th>
                <th>Type</th>
                <th>Amount</th>
                <th>Remarks</th>
                <th>Image</th>
                <th>Status</th>
            </tr>
        );
    }

    getTableBody() {
        return (
            <tbody className="table_body">
            <tr>
                <td>SN</td>
                <td>1</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>Status</td>
            </tr>
            <tr>
                <td>SN</td>
                <td>1</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td className="product_image pointer">View File
                </td>
                <td>Status</td>
            </tr>
            <tr>
                <td>SN</td>
                <td>1</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td className="product_image pointer">View File
                </td>
                <td>Status</td>
            </tr>
            </tbody>
        );
    }

    componentDidMount() {
        Axios
            .get('getClaims')
            .then(response => {
                if (response.data.success) {
                    console.log('success');
                }
                else {
                    console.log('fail');
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
                        <h4 className="">Open Claims</h4>
                        <div className="card-body text-center">
                           <h2>4</h2>
                        </div>
                    </div>
                    <div className="card bg-success p-30 claimHistoryHeader">
                        <h4>Closed Claims</h4>
                        <div className="card-body text-center">
                            <h2>9</h2>
                        </div>
                    </div>
                    <div className="card bg-warning p-30 claimHistoryHeader">
                        <h4>Rejected Claims</h4>
                        <div className="card-body text-center">
                            <h2>2</h2>
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
            </div>


        );
    }
}
export default ClaimHistory;
