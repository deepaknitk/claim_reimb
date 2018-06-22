import React from 'react';
import {Link} from 'react-router-dom';
class IndexPage extends React.Component {
    constructor(props, context) {
        super(props, context);
    }
    render() {
        return(
            <div>
                <h1 className="text-center"> Welcome to Lead Tracking System</h1>
                <h6 className="text-center"><Link to="/rfq">Create a RFQ</Link></h6>
            </div>
        );
    }
}
export default IndexPage;
