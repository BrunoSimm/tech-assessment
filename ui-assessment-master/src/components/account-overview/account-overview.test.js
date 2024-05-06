import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import AccountOverview from './account-overview';

describe('AccountOverview', () => {
  const mockData = {
    supportContact: {
      name: 'Test Name',
      email: 'test@example.com'
    },
    salesOverview: {
      uploads: 10,
      successfulUploads: 8,
      linesAttempted: 100,
      linesSaved: 80,
      lastUploadDate: 1633027200
    }
  };

  it('renders without crashing', () => {
    render(<AccountOverview data={mockData} />);
    expect(screen.getByText('Account Overview')).toBeInTheDocument();
  });

  it('renders Account Overview', () => {
    render(<AccountOverview data={mockData} />);
    expect(screen.getByText('Account Overview')).toBeInTheDocument();
  });
});