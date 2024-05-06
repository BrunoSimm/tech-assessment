import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import { SupportContact } from './support-contact';

describe('SupportContact', () => {
    const mockData = {
        name: 'Test User',
        email: 'testuser@example.com',
    };

    it('renders without crashing', () => {
        render(<SupportContact data={mockData} />);
        expect(screen.getByText('YOUR FEEFO SUPPORT CONTACT')).toBeInTheDocument();
    });

    it('displays the correct name', () => {
        render(<SupportContact data={mockData} />);
        expect(screen.getByText(mockData.name)).toBeInTheDocument();
    });

    it('displays the correct email', () => {
        render(<SupportContact data={mockData} />);
        expect(screen.getByText(mockData.email)).toBeInTheDocument();
    });

    it('displays the correct initial in the yellow box', () => {
        render(<SupportContact data={mockData} />);
        expect(screen.getByText(mockData.name.charAt(0).toUpperCase())).toBeInTheDocument();
    });
});