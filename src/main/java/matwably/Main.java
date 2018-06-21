package matwably;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;
import matwably.ast.*;
import matwably.ast.List;
import natlab.tame.BasicTamerTool;
import natlab.tame.tir.*;
import natlab.tame.valueanalysis.IntraproceduralValueAnalysis;
import natlab.tame.valueanalysis.ValueAnalysis;
import natlab.tame.valueanalysis.value.Args;
import natlab.tame.valueanalysis.aggrvalue.AggrValue;
import natlab.tame.valueanalysis.basicmatrix.BasicMatrixValue;
import natlab.toolkits.filehandling.FileFile;
import natlab.toolkits.filehandling.GenericFile;
import natlab.toolkits.path.FileEnvironment;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static void log(Object str){
        System.out.println(str.toString());
    }
    public static void main(String[] argv)
    {
        Module mod = new Module();
        mod.setIdentifier(new Identifier("done"));
        TypeUse type_use = new TypeUse(new Opt<>(new Identifier("i")), new ValueType.F32());
        TypeUse type_use2 = new TypeUse(new Opt<>(new Identifier("j")), new ValueType.F32());

        List<TypeUse> locals = new List<>();
        List<ValueType> values = new List<>();
        Expression body = new Expression();
        values.add(new ValueType.F32());
        values.add(new ValueType.F64());
        locals.add(type_use);
        locals.add(type_use2);
        WasmFunction func = new WasmFunction(new Opt<>(new Identifier("david")),locals, locals, values,body);
        mod.addFunctions(func);
        PrettyPrinter pretty = new PrettyPrinter(mod);
        log(pretty.display()+"dasdas");
//        List<Instruction> da = func.getBody().getExpression().getInstructionsList();
//        func.addChild();
        //System.out.println(ValueType.I32.class);
        CommandLineOptions opts = new CommandLineOptions();
        JCommander optParse = null;
        try{
            optParse = new JCommander(opts, argv);
        }catch(ParameterException e)
        {
            System.err.println(e.getMessage());
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
        Generator generator = new Generator(generic_files);
        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode("david");
//        for(byte b : byteBuffer.array()){
//            System.out.println(b);
//        }
        Double a = 1321313.0;
        double d = 1321313.99999;
        byte[] output = new byte[8];
        long lng = Double.doubleToLongBits(d);
        for(int i = 0; i < 8; i++) output[i] = (byte)((lng >> ((7 - i) * 8)) & 0xff);

//        System.out.println(output);

    }



}

@Parameters(separators = " ")
final class CommandLineOptions {
    @Parameter
    public ArrayList<String> input_files = new ArrayList<String>();

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
            System.err.println("Error: Invalid format for arguments to entry function, check usage\n");
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
            System.err.println("Error: No input files");
            System.exit(0);
        }
        HashSet<String> set_names = new HashSet<>();
        this.input_files.forEach((file_path)->{
            GenericFile gen_file = GenericFile.create(file_path);
            if(!set_names.contains(gen_file.getName()))
            {
                if(!gen_file.exists())
                {
                    System.err.printf("Error: Path to Matlab file %s does not exist, \n",file_path);
                    System.exit(0);
                }

                gen_list.add(gen_file);
            }else{
                System.err.printf("Error: Two of the input files contain name, %s\n",gen_file.getName());
                System.exit(0);
            }

        });
        return gen_list;
    }



}
