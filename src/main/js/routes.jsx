import React from 'react';
import { Switch, Route } from 'react-router-dom';
import SubmitClaim from './containers/Claimcontainer/SubmitClaim';
import NotFoundPage from './components/PageNotFound';
// import IndexPage from './containers/IndexPage';
import Login from './components/Login';
import Profile from './components/Profile';
import Register from './components/Register';
import SubmitSuccess from './components/SubmitSuccess';
import IndexPage from './containers/IndexPage';
import ClaimHistory from './containers/ClaimHistory';
import ManageClaims from './containers/ManageClaims';
function buildRoutes(props) {
    return (
        <Switch>
            <Route path="/" exact render = {() => <Login property={props}/>}/>
            <Route path="/submitSuccess" exact render = {()=> <SubmitSuccess/>}/>
            <Route path="/profile" exact render = {() => <Profile user={props.user}/>}/>
            <Route path="/register" component={Register}/>

            <Route path="/createClaim" exact render={() => <SubmitClaim {...props}/>}/>
            <Route path="/dashboard" exact component={ClaimHistory}/>
            <Route path="/manageClaims" exact component={ManageClaims}/>
            <Route path="*" component={NotFoundPage}/>
        </Switch>
    );
}
export default buildRoutes;
