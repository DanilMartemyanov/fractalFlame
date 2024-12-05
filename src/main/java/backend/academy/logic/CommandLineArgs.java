package backend.academy.logic;

import backend.academy.transformation.Transformation;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import lombok.Getter;
import java.util.List;

@Getter
public class CommandLineArgs {

    @Parameter(names = "--width")
    private int width;

    @Parameter(names = "--height")
    private int height;

    @Parameter(names = "--eqCount")
    private int eqCount;

    @Parameter(names = "--iter")
    private int iterations;

    @Parameter(names = "--satur")
    private int saturations;

    @Parameter(names = "--transform")
    private List<String> transformations;

    @Parameter(names = "--format")
    private String fileFormat;

    public static CommandLineArgs parseArguments(String[] args) {
        CommandLineArgs cmdArgs = new CommandLineArgs();
        JCommander jCommander = JCommander.newBuilder()
            .addObject(cmdArgs)
            .build();

        jCommander.parse(args);
        return cmdArgs;
    }


}
