import React from 'react';
import { Switch, Route } from 'react-router-dom';
import RFQForm from './containers/RFQ/RFQForm';
import NotFoundPage from './components/PageNotFound';
import IndexPage from './containers/IndexPage';
import SubmitSuccess from './components/SubmitSuccess';
function buildRoutes(props) {
    return (
        <Switch>
            <Route path="/" exact render={() => <IndexPage {...props}/>}/>
            <Route path="/rfq" exact render={() => <RFQForm {...props}/>}/>
            <Route path="/submitSuccess" exact render = {()=> <SubmitSuccess/>}/>
            <Route path="*" component={NotFoundPage}/>
        </Switch>
    );
}
export default buildRoutes;
