import React from 'react';
// import Login from '../components/Login';
// import Register from '../components/Register';
// import Profile from '../components/Profile';

class IndexPage extends React.Component {
    constructor(props, context) {
        super(props, context);
    }
    render() {
        return(
            <div>

                <h1 className="text-center"> Welcome</h1>
                <h6 className="text-center"><Link to="/createClaim">Submit Your Claim</Link></h6>
                {/*<Login/>*/}
                {/*<Register/>*/}
                {/*<Profile/>*/}

            </div>
        );
    }
}
export default IndexPage;
