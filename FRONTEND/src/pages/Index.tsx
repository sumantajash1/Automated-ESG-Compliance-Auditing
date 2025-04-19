import { FileText } from "lucide-react";
import { Button } from "@/components/ui/button";
import { Link } from "react-router-dom";

const Index = () => {
  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gradient-to-b from-gray-50 to-gray-100">
      <div className="max-w-3xl text-center p-8 rounded-lg bg-white shadow-sm border">
        <div className="flex justify-center mb-6">
          <div className="p-3 bg-green-100 rounded-full">
            <FileText className="h-10 w-10 text-green-600" />
          </div>
        </div>
        
        <h1 className="text-4xl font-bold mb-4 text-gray-800">Ethical Supply Chain Upload Hub</h1>
        
        <p className="text-xl text-gray-600 mb-8">
          A secure platform for suppliers to submit compliance documentation for transparent, ethical supply chain auditing.
        </p>
        
        <div className="flex flex-col sm:flex-row justify-center gap-4">
          <Button asChild className="bg-green-600 hover:bg-green-700">
            <Link to="/supplier-upload">
              Go to Document Upload
            </Link>
          </Button>
          
          <Button variant="outline">
            Learn More
          </Button>
        </div>
        
        <div className="mt-12 grid grid-cols-1 md:grid-cols-4 gap-6">
          <div className="p-4 bg-blue-50 rounded-lg border border-blue-100">
            <h3 className="font-semibold text-blue-700 mb-2">Business</h3>
            <p className="text-sm text-gray-600">Registration & tax documents</p>
          </div>
          
          <div className="p-4 bg-green-50 rounded-lg border border-green-100">
            <h3 className="font-semibold text-green-700 mb-2">Environmental</h3>
            <p className="text-sm text-gray-600">Sustainability certifications</p>
          </div>
          
          <div className="p-4 bg-purple-50 rounded-lg border border-purple-100">
            <h3 className="font-semibold text-purple-700 mb-2">Labor</h3>
            <p className="text-sm text-gray-600">Fair work practices</p>
          </div>
          
          <div className="p-4 bg-amber-50 rounded-lg border border-amber-100">
            <h3 className="font-semibold text-amber-700 mb-2">Financial</h3>
            <p className="text-sm text-gray-600">Transparency records</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Index;
