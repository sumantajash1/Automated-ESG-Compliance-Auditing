
import { useState } from "react";
import { 
  Briefcase, 
  Leaf, 
  DollarSign, 
  Handshake, 
  LogOut,
  FileText,
  CheckCircle2,
  BadgeInfo
} from "lucide-react";
import { Button } from "@/components/ui/button";
import { CategorySection } from "@/components/CategorySection";
import { toast } from "@/components/ui/sonner";
import { Card, CardHeader, CardContent, CardFooter } from "@/components/ui/card";
import { Progress } from "@/components/ui/progress";

export default function SupplierUpload() {
  const [uploadProgress, setUploadProgress] = useState(0);
  const supplierName = "Eco Textiles Co."; // This would come from auth or state

  // Mock function to handle logout
  const handleLogout = () => {
    toast("Logged out successfully");
    // In a real app, this would handle the actual logout logic
  };

  // Mock function to handle submit
  const handleSubmit = () => {
    // Simulate upload progress
    let progress = 0;
    const interval = setInterval(() => {
      progress += 10;
      setUploadProgress(progress);
      
      if (progress >= 100) {
        clearInterval(interval);
        toast.success("Documents uploaded successfully!");
      }
    }, 300);
  };

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Header/Navbar */}
      <header className="bg-white border-b shadow-sm">
        <div className="container mx-auto px-4 py-4 flex justify-between items-center">
          <div className="flex items-center space-x-2">
            <FileText className="h-6 w-6 text-green-600" />
            <span className="text-xl font-semibold">Ethical Supply Audit</span>
          </div>
          
          <div className="flex items-center space-x-4">
            <span className="text-gray-700">Welcome, {supplierName}</span>
            <Button 
              variant="outline" 
              size="sm" 
              onClick={handleLogout}
              className="flex items-center space-x-1"
            >
              <LogOut className="h-4 w-4" />
              <span>Logout</span>
            </Button>
          </div>
        </div>
      </header>

      <main className="container mx-auto px-4 py-8">
        {/* Page Title & Instructions */}
        <Card className="mb-8">
          <CardHeader>
            <div className="flex items-start space-x-4">
              <div className="p-3 bg-blue-100 rounded-full">
                <FileText className="h-6 w-6 text-blue-600" />
              </div>
              <div>
                <h1 className="text-2xl font-bold text-gray-900">Upload Your Compliance Documents</h1>
                <p className="text-gray-600 mt-2">
                  To get started, please upload the required documents. This helps us ensure transparency, 
                  ethics, and sustainability across our supply chain.
                </p>
              </div>
            </div>
          </CardHeader>
          <CardContent>
            <div className="flex items-center space-x-3 p-3 bg-blue-50 rounded-lg">
              <BadgeInfo className="h-5 w-5 text-blue-600 flex-shrink-0" />
              <p className="text-sm text-blue-700">
                All documents are securely stored and will only be accessible to authorized auditors.
                For any questions, please contact support@ethicalsupplychain.com
              </p>
            </div>
          </CardContent>
        </Card>

        {/* Upload Form Sections */}
        <div className="space-y-6">
          <CategorySection
            title="Business Compliance"
            icon={<Briefcase className="h-5 w-5" />}
            description="Upload official documents related to your business registration and tax compliance."
            color="bg-blue-600"
            documents={[
              {
                id: "business-reg",
                label: "Business Registration Certificate",
                description: "Official document proving your business is legally registered with appropriate government authorities.",
                acceptedFileTypes: ".pdf,.jpg,.png,.jpeg",
                maxFileSizeMB: 5
              },
              {
                id: "tax-cert",
                label: "GST Certificate / Tax Information",
                description: "Document showing your tax registration number and compliance with tax regulations.",
                acceptedFileTypes: ".pdf,.jpg,.png,.jpeg",
                maxFileSizeMB: 5
              },
              {
                id: "business-license",
                label: "Business License",
                description: "Any industry-specific licenses required for your operations.",
                acceptedFileTypes: ".pdf,.jpg,.png,.jpeg",
                maxFileSizeMB: 5
              }
            ]}
          />

          <CategorySection
            title="Environmental Compliance"
            icon={<Leaf className="h-5 w-5" />}
            description="Provide documentation showing your adherence to environmental regulations and sustainability practices."
            color="bg-green-600"
            documents={[
              {
                id: "emission-reports",
                label: "Emission Reports / Certificates",
                description: "Documents showing your carbon emissions and any certification for environmental compliance.",
                acceptedFileTypes: ".pdf,.doc,.docx,.xls,.xlsx",
                maxFileSizeMB: 10
              },
              {
                id: "waste-management",
                label: "Waste Management Reports",
                description: "Reports showing your waste disposal and recycling practices.",
                acceptedFileTypes: ".pdf,.doc,.docx,.xls,.xlsx",
                maxFileSizeMB: 10
              },
              {
                id: "iso-cert",
                label: "ISO Certifications",
                description: "ISO 14001 or other environmental management system certifications.",
                acceptedFileTypes: ".pdf,.jpg,.png,.jpeg",
                maxFileSizeMB: 5
              }
            ]}
          />

          <CategorySection
            title="Labour Ethics"
            icon={<Handshake className="h-5 w-5" />}
            description="Upload documents that demonstrate fair and ethical treatment of workers in your organization."
            color="bg-purple-600"
            documents={[
              {
                id: "employee-welfare",
                label: "Employee Welfare Policy",
                description: "Documents detailing your policies for employee welfare, health, and safety.",
                acceptedFileTypes: ".pdf,.doc,.docx",
                maxFileSizeMB: 5
              },
              {
                id: "wage-proof",
                label: "Minimum Wage Proof / Labour Contracts",
                description: "Evidence of fair compensation practices and sample labour contracts.",
                acceptedFileTypes: ".pdf,.doc,.docx,.jpg,.png",
                maxFileSizeMB: 10
              },
              {
                id: "audit-reports",
                label: "Previous Audit Reports",
                description: "Any previous social compliance audit reports if available.",
                acceptedFileTypes: ".pdf,.doc,.docx,.xls,.xlsx",
                maxFileSizeMB: 10
              }
            ]}
          />

          <CategorySection
            title="Financial Transparency"
            icon={<DollarSign className="h-5 w-5" />}
            description="Provide financial documents that demonstrate the financial health and transparency of your business."
            color="bg-amber-600"
            documents={[
              {
                id: "audit-report",
                label: "Annual Audit Report",
                description: "Financial audit report for the last fiscal year.",
                acceptedFileTypes: ".pdf,.xls,.xlsx",
                maxFileSizeMB: 10
              },
              {
                id: "tax-filing",
                label: "Tax Filing Receipts",
                description: "Proof of tax filings for the most recent tax period.",
                acceptedFileTypes: ".pdf,.jpg,.png,.jpeg",
                maxFileSizeMB: 5
              }
            ]}
          />
        </div>

        {/* Submit Section */}
        <div className="mt-10 p-6 bg-white rounded-lg border shadow-sm">
          {uploadProgress > 0 && (
            <div className="mb-6">
              <div className="flex justify-between items-center mb-2">
                <span className="text-sm font-medium text-gray-700">Uploading documents...</span>
                <span className="text-sm text-gray-600">{uploadProgress}%</span>
              </div>
              <Progress value={uploadProgress} className="h-2" />
            </div>
          )}
          
          <div className="flex justify-between items-center">
            <p className="text-sm text-gray-600">
              By submitting these documents, you confirm their authenticity and accuracy.
            </p>
            <Button 
              onClick={handleSubmit} 
              className="bg-green-600 hover:bg-green-700 text-white"
              disabled={uploadProgress > 0 && uploadProgress < 100}
            >
              {uploadProgress === 100 ? (
                <span className="flex items-center">
                  <CheckCircle2 className="mr-2 h-4 w-4" /> Submitted
                </span>
              ) : (
                "Submit Documents"
              )}
            </Button>
          </div>
        </div>
      </main>

      <footer className="mt-auto py-6 bg-gray-100">
        <div className="container mx-auto px-4 text-center text-sm text-gray-600">
          <p>&copy; 2025 Ethical Supply Chain Audit. All rights reserved.</p>
          <p className="mt-1">We prioritize security, transparency, and ethical business practices.</p>
        </div>
      </footer>
    </div>
  );
}
