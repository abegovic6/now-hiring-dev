import * as ReactDOM from 'react-dom';
import * as React from 'react';
import { DatePickerComponent } from '@syncfusion/ej2-react-calendars';
//import './monthpicker-style.css';

function MonthPicker() {
    const start = 'Year';
    const depth = 'Year';
    const format = 'MMMM y';
    const dateValue = new Date();

    return (
        <div className='control-pane'>
            <div className='control-section'>
                <div className='datepicker-control-section'>
                    <DatePickerComponent value={dateValue} start={start} depth={depth} format={format}></DatePickerComponent>
                </div>
            </div>
        </div>
    )
}
export default MonthPicker;