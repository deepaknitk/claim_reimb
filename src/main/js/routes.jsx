import React from 'react';
import { Switch, Route } from 'react-router-dom';
import RFQForm from './containers/RFQ/RFQForm';
import NotFoundPage from './components/PageNotFound';
// import IndexPage from './containers/IndexPage';
import Login from './components/Login';
import Profile from './components/Profile';
import Register from './components/Register';
import SubmitSuccess from './components/SubmitSuccess';
function buildRoutes(props) {
    return (
        <Switch>
            <Route path="/" exact render = {() => <Login property={props}/>}/>
            <Route path="/rfq" exact render={() => <RFQForm {...props}/>}/>
            <Route path="/submitSuccess" exact render = {()=> <SubmitSuccess/>}/>
            <Route path="/profile" exact render = {() => <Profile user={props.user}/>}/>
            <Route path="/register" component={Register}/>
            <Route path="*" component={NotFoundPage}/>
        </Switch>
    );
}
export default buildRoutes;
