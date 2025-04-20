
import { useState } from "react";
import { cn } from "@/lib/utils";
import { ChevronDown, ChevronUp } from "lucide-react";
import { FileUpload } from "@/components/FileUpload";

interface DocumentField {
  id: string;
  label: string;
  description: string;
  acceptedFileTypes?: string;
  maxFileSizeMB?: number;
}

interface CategorySectionProps {
  title: string;
  icon: React.ReactNode;
  description: string;
  color: string;
  documents: DocumentField[];
}

export function CategorySection({
  title,
  icon,
  description,
  color,
  documents,
}: CategorySectionProps) {
  const [expanded, setExpanded] = useState(true);
  
  return (
    <div className="mb-8 border rounded-lg shadow-sm overflow-hidden">
      <div 
        className={cn(
          "flex items-center justify-between p-4 cursor-pointer",
          color
        )}
        onClick={() => setExpanded(!expanded)}
      >
        <div className="flex items-center space-x-3">
          <span className="text-white">{icon}</span>
          <h2 className="text-lg font-medium text-white">{title}</h2>
        </div>
        <button className="text-white">
          {expanded ? (
            <ChevronUp className="h-5 w-5" />
          ) : (
            <ChevronDown className="h-5 w-5" />
          )}
        </button>
      </div>
      
      {expanded && (
        <div className="p-4 bg-white">
          <p className="text-sm text-gray-600 mb-4">{description}</p>
          <div className="space-y-4">
            {documents.map((doc) => (
              <FileUpload
                key={doc.id}
                label={doc.label}
                description={doc.description}
                acceptedFileTypes={doc.acceptedFileTypes}
                maxFileSizeMB={doc.maxFileSizeMB}
              />
            ))}
          </div>
        </div>
      )}
    </div>
  );
}
