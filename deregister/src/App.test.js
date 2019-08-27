import React from 'react';
import { shallow , mount} from 'enzyme';
import App from './App';
import ReviewButton from './components/ReviewButton';

const reviewButton = shallow(<ReviewButton/>);
it('review button renders correctly', () => {
  expect(reviewButton).toMatchSnapshot();
});
