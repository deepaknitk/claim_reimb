import React from 'react';

const Table = (props) => {
   const tableColumn = props.getTableHeader();
   const tablBody = props.getTableBody();
    return (
        <div>
            <div style ={{backgroundColor: 'white'}}>
                <div className="row">
                    <div className="col-md-12">
                        <div className="table-responsive">
                            <table id="mytable" className= "table table-bordred">
                                 <thead className={props.headerCssClassName}>
                                    {tableColumn}
                                 </thead>
                                    {tablBody}
                            </table>
                        </div>
                        <div>{props.isTableEmpty}</div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Table;
