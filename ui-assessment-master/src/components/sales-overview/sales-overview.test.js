import React from 'react';
import { render, screen } from '@testing-library/react';
import {SalesOverview} from "./sales-overview";

describe('SalesOverview', () => {
    const mockData = {
        successfulUploads: 100,
        uploads: 200,
        linesSaved: 300,
        linesAttempted: 400,
    };

    it('renders without crashing', () => {
        render(<SalesOverview data={mockData} />);
    });

    it('displays the correct upload success percentage', () => {
        render(<SalesOverview data={mockData} />);
        const uploadSuccess = screen.getByText('50.00%');
        expect(uploadSuccess).toBeInTheDocument();
    });

    it('displays the correct lines saved percentage', () => {
        render(<SalesOverview data={mockData} />);
        const linesSaved = screen.getByText('75.00%');
        expect(linesSaved).toBeInTheDocument();
    });
});