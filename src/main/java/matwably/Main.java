package matwably;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;
import natlab.tame.BasicTamerTool;
import natlab.tame.tir.*;
import natlab.tame.valueanalysis.IntraproceduralValueAnalysis;
import natlab.tame.valueanalysis.ValueAnalysis;
import natlab.tame.valueanalysis.value.Args;
import natlab.tame.valueanalysis.aggrvalue.AggrValue;
import natlab.tame.valueanalysis.basicmatrix.BasicMatrixValue;
import natlab.toolkits.filehandling.FileFile;
import natlab.toolkits.filehandling.GenericFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] argv)
    {
        CommandLineOptions opts = new CommandLineOptions();
        JCommander optParse = null;
        try{
            optParse = new JCommander(opts, argv);
        }catch(ParameterException e)
        {
            System.err.println( e.getMessage());
            System.exit(1);

        }

        optParse.setProgramName("MatWably");

        if( opts.help )
        {
            optParse.usage();
            System.exit(0);
        }
        String output_file = opts.output_file;
        ArrayList<String> args_entry_function = opts.getEntryFunctionArgs(optParse);
        ArrayList<GenericFile> generic_files = opts.getFunctionFiles();


    }



}

@Parameters(separators = " ")
final class CommandLineOptions {
    @Parameter
    public List<String> input_files = new ArrayList<String>();

    @Parameter(names = {"-h","--help"}, hidden = true)
    public boolean help = false;

    @Parameter(names = {"-o","--output-file"},description = "Outfile to place code")
    public String output_file = "";

    // TODO Check args for entry function are empty
    @Parameter(names = {"-a","--args"},
            description = "Arguments for entry function, e.g."+"'[\"DOUBLE&1*1&REAL\",\"DOUBLE&1*1&REAL\"]'\n"+
            "\t\t\t\t\t   Representing two parameters, both, double, 1-by-1, real Matlab matrices")
    public String args = "";

    @Parameter(names={ "--time" }, arity=1, description="time compilation time")
    public boolean timeCompilation = false;
    /**
     * Parses the entry function parameters
     * @param commander
     * @return
     */
    public ArrayList<String> getEntryFunctionArgs( JCommander commander)
    {
        //e.g.'["DOUBLE&1*1&REAL","DOUBLE&1*1&REAL","DOUBLE&1*1&REAL","DOUBLE&1*1&REAL"]'

        if(!this.args.isEmpty() && (!this.args.contains("'[")|| !this.args.contains("]'")))
        {
            System.err.println("Invalid format for arguments to entry function, check usage\n");
            commander.usage();
            System.exit(1);
        }
        return  new ArrayList<>(
                Arrays.asList(this.args.replaceAll("'\\[|\\]'","")
                        .split(",")));

    }

    /**
     * Processes the matlab input files and creates generic matlab files
     * @return Returns an ArrayList of Generic MatLab files using natlab.toolkits.filehandling
     * @see natlab.toolkits.filehandling.GenericFile
     */
    public ArrayList<GenericFile> getFunctionFiles()
    {
        ArrayList<GenericFile> gen_list = new ArrayList<>();
        if(this.input_files.size() == 0)
        {
            System.err.println("No input files");
            System.exit(0);
        }
        this.input_files.forEach((file_path)->{
            GenericFile gen_file = GenericFile.create(file_path);
            if(!gen_file.exists())
            {
                System.err.printf("Path to Matlab file %s does not exist, \n",file_path);
                System.exit(0);
            }
            gen_list.add(gen_file);
        });
        return gen_list;
    }



}
